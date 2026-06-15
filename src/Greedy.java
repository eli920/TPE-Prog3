
// Estrategia Greedy:
//  Se ordenan los paquetes de mayor a menor peso, priorizando los que más impactan el peso no asignado si quedan afuera. 
//  Para cada paquete se selecciona el camión válido con menor capacidad restante que pueda alojar el paquete, 
// minimizando el desperdicio de espacio.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Greedy {
    private List<Paquete> paquetes;
    private int estadosGenerados;

    public Greedy(List<Paquete> paquetes) {
        this.paquetes = paquetes;
        this.estadosGenerados = 0;
    }

    public List<Paquete> asignarPaquetes(List<Camion> camiones) {

        List<Paquete> paquetesOrdenados = new ArrayList<>(paquetes);
        paquetesOrdenados.sort(Comparator.comparingInt(Paquete::getPeso).reversed());

        List<Paquete> noAsignados = new ArrayList<>();

        for (Paquete paquete : paquetesOrdenados) {
            Camion mejorCamion = null;
            int menorCapacidadRestante = Integer.MAX_VALUE;

            for (Camion camion : camiones) {
                estadosGenerados++;
                if (camion.puedeAgregarPaquete(paquete) && ((camion.getCapacidad()-camion.getPeso()) < menorCapacidadRestante)) {
                    mejorCamion = camion;
                    menorCapacidadRestante = camion.getCapacidad() - camion.getPeso();
                }
            }

            if (mejorCamion != null) {
                mejorCamion.agregarPaquete(paquete);
            } else {
                noAsignados.add(paquete);
            }
        }
        
        return noAsignados;
    }  


    public int getEstadosGenerados() {
        return estadosGenerados;
    }
    
}
