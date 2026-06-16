import java.util.*;

public class Greedy {

    private int pesoSinCamion = 0; // peso del paquete mas el peso acumulado para saber si
    private int estadosGenerados = 0;
    private List<Paquete> paquetesNoAsignados = new  ArrayList<>();
    List<Camion>S;

    List<Camion> greedy(List<Paquete> paquetes, List<Camion> camiones) {
        S = new ArrayList<>(camiones);
        //ordenos los camniones de mayor peso a menor


        //odenar los paquetes de mayor a menor
        List<Paquete>paquetesOrdenados = new ArrayList<>(paquetes);
        //Collections.sort(camiones);
        paquetesOrdenados.sort(Comparator.comparingInt(Paquete::getPeso).reversed());



        while (!paquetesOrdenados.isEmpty()){
            Paquete x = paquetesOrdenados.get(0);
            paquetesOrdenados.remove(0);

            if (factible(x, S)){
                //S.add(p);
                estadosGenerados++;
            }


        }

        return S;
    }

    private boolean factible(Paquete p, List<Camion> S) {

        for (Camion c : S) {
            if (c.puedeCargar(p)) {
                c.cargarPaquete(p);
                return true;
            }
        }

        //debe evaluar que el paquete entre en el camion, es decir el camion debe tener espacion para los kilos del paquete
        pesoSinCamion += p.getPeso();
        paquetesNoAsignados.add(p);
        return false;
    }


    public void presentarResultados() {
        System.out.println("Greedy");
        System.out.println("Peso no asignado a camion: " + pesoSinCamion + " kg.");
        System.out.println("Estados generados: " + estadosGenerados);
        System.out.println("Asignación por camión:");
        for (Camion c : S) {
            System.out.println(c);
        }
    }



}
