import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsignadorPaquetes {

    private List<Paquete> paquetes;
    private List<Camion> camiones;
    private int mejorPesoNoAsignado; // el objetivo es que si sobran paquetes, encontrrar  el menor peso posible entre los que sobran
    private Map<Integer, List<Paquete>> mejorAsignacion; //
    private int estadosGenerados; //el enunciado pide llevar un control de los estados generados

    //bactraking
    public void asignarPaquetes( List<Paquete> paquetes, List<Camion> camiones) {
        this.paquetes = paquetes;
        this.camiones = camiones;
        this.mejorAsignacion = new HashMap<>();
        this.estadosGenerados = 0;
        this.mejorPesoNoAsignado = Integer.MAX_VALUE;


        asignar(0,0);
        presentarResultados();
    }

    private void asignar(int indice, int pesoNoAsignadoActual) {
        estadosGenerados++;

        // --- PODA POR OPTIMALIDAD (Al inicio para máxima eficiencia) ---
        if (pesoNoAsignadoActual >= mejorPesoNoAsignado) {
            return;
        }

        // CASO BASE: Se procesaron todos los paquetes
        if (indice == paquetes.size()) {
                mejorPesoNoAsignado = pesoNoAsignadoActual;
                registrarMejorSolucion();
                return;
        }

        Paquete p = paquetes.get(indice);

        // OPCIÓN 1: Intentar asignar el paquete a cada camión disponible
        for (Camion c : camiones) {
            if (c.puedeCargar(p)){ // Función de FACTIBILIDAD (Poda por restricciones)
                c.cargarPaquete(p);
                asignar(indice + 1, pesoNoAsignadoActual);
                c.descargar(p);      // --- DESHACER ---
            }


        }
        // OPCIÓN 2: No asignar el paquete (UNA SOLA VEZ, FUERA DEL BUCLE)
        // Se suma el peso del paquete actual al peso perdido
        asignar(indice + 1, pesoNoAsignadoActual + p.getPeso());


    }




    private void registrarMejorSolucion() {
        mejorAsignacion.clear();
        for (Camion camion : camiones) {
            // IMPORTANTE: Crear una copia nueva de la lista para que no se vacíe al retroceder
            mejorAsignacion.put(camion.getIdCamion(), new ArrayList<>(camion.getPaquetesAsignados()));
        }
    }

    private void presentarResultados(){
        System.out.println("Backtracking");
        System.out.println("Peso no asignado: " + mejorPesoNoAsignado + " kg.");
        System.out.println("Estados generados: " + estadosGenerados);
        System.out.println("Asignación por camión:");
        for (Map.Entry<Integer, List<Paquete>> e : mejorAsignacion.entrySet()) {
            System.out.println("Camión " + e.getKey() + ": " + e.getValue());
        }
    }



}
