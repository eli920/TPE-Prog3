
// Estrategia Greedy:
// Se ordenan los paquetes de mayor a menor peso, priorizando los que más impactan el peso no asignado si quedan afuera. 
// Para cada paquete se selecciona el camión válido con menor capacidad restante que pueda alojar el paquete, 
// minimizando el desperdicio de espacio.
// Si el paquete contiene alimentos, sólo se consideran camiones refrigerados.
// Si el paquete no contiene alimentos, primero se consideran camiones no refrigerados; si ninguno puede transportarlo,
//  se consideran camiones refrigerados.
// De esta forma se intenta reservar los camiones refrigerados, que son más flexibles.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Greedy {
    private List<Paquete> paquetes;
    private List<Paquete> noAsignados;
    private int pesoNoAsignado;
    private int estadosGenerados;

    public Greedy(List<Paquete> paquetes) {
        this.paquetes = paquetes;
        this.noAsignados = new ArrayList<>();
        this.pesoNoAsignado=0;
        this.estadosGenerados = 0;
    }

    public List<Camion> asignarPaquetes(List<Camion> camiones) {

        List<Paquete> paquetesOrdenados = new ArrayList<>(paquetes);
        paquetesOrdenados.sort(Comparator.comparingInt(Paquete::getPeso).reversed());

        //No hace falta ordenar porque con los if ya manejo la prioridad.
        // List<Camion> camionesOrdenados= new ArrayList<>(camiones);
        // camionesOrdenados.sort(Comparator.comparing(Camion::estaRefrigerado)); 


        for (Paquete paquete : paquetesOrdenados) {
             Camion mejorCamion = null;

             if (paquete.contieneAlimentos()) {

                mejorCamion = buscarMejorCamion(paquete, camiones, true);

            } else {

                mejorCamion = buscarMejorCamion(paquete, camiones, false);

                if (mejorCamion == null) {

                    mejorCamion = buscarMejorCamion(paquete, camiones, true);
                }
            }

           

            if (mejorCamion != null) {
                mejorCamion.agregarPaquete(paquete);
            } else {
                noAsignados.add(paquete);
                pesoNoAsignado += paquete.getPeso();
            }
        }
        
       

        return camiones;
    } 

    private Camion buscarMejorCamion(Paquete paquete, List<Camion> camiones, boolean necesitaRefrigerado) {
        Camion mejorCamion = null;
        int menorCapacidadRestante = Integer.MAX_VALUE;

            for (Camion camion : camiones) {
                estadosGenerados++;
                
                if (camion.estaRefrigerado() == necesitaRefrigerado
                                    && camion.puedeAgregarPaquete(paquete)
                                    && ((camion.getCapacidad()-camion.getPeso()) < menorCapacidadRestante)) {
 
                        mejorCamion = camion;
                        menorCapacidadRestante = camion.getCapacidad() - camion.getPeso();
                    
                
                }
            }

        return mejorCamion;
            
    }        


    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public List<Paquete> getNoAsignados() {
        return noAsignados;
    }

    public int getPesoNoAsignado() {
        return pesoNoAsignado;
    }
    
}
