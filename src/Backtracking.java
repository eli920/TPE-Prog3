import java.util.ArrayList;
import java.util.List;


//  Estrategia Backtracking:
//  Para cada paquete se exploran todas las alternativas posibles de asignación a los camiones disponibles.
//  Se agrega un camión especial denominado DEPÓSITO que representa la decisión de dejar un paquete sin asignar.
//  Se busca minimizar el peso total de paquetes no asignados.
//  Como poda, se descartan ramas cuyo peso no asignado ya supera la mejor solución encontrada.


public class Backtracking {

    private List<Paquete> paquetes;
    private List<Camion> solucion;
    private int pesoNoAsignadoMin;
    private int estadosGenerados; // Variable para contar los estados generados durante el proceso de backtracking

    public Backtracking(List<Paquete> paquetes) {
        this.paquetes = paquetes;
        this.solucion = new ArrayList<>();
        this.pesoNoAsignadoMin = Integer.MAX_VALUE;
        this.estadosGenerados = 0;
    }

    

    public List<Camion> asignarPaquetes(List<Camion> camiones) {
        // Se crea un nuevo camión que va a funcionar como depósito de paquetes no asignados.
        Camion deposito = new Camion(-1, "DEPOSITO", true, Integer.MAX_VALUE);
    
        // Se crea un estado actual con la lista de los camiones disponibles y se le agrega el camión que se va a usar como depósito
        // Se crea este estado actual para no modificar la lista de camiones original.
        List<Camion> estadoActual = new ArrayList<>(camiones);
        estadoActual.add(deposito);
        
        int posPaquete = 0;

        asignar(estadoActual, posPaquete);

        return solucion;

    }

    private void asignar(List<Camion> estadoActual, int posPaquete) {

        estadosGenerados++; // se incrementa cada vez que se llama al método asignar 

        if (posPaquete == paquetes.size()) {
            int pesoNoAsignado = estadoActual.get(estadoActual.size() -1).getPeso(); // Peso del camión depósito
            if (pesoNoAsignado < pesoNoAsignadoMin) {
                pesoNoAsignadoMin = pesoNoAsignado;

                solucion.clear();
                
                for (Camion c : estadoActual) {
                    solucion.add(c.copia());// Se guarda una copia de la mejor solucion encontrada hasta el momento.
                }
                
            }
           
        }else{

             Paquete paqueteActual = paquetes.get(posPaquete);

            for (int i = 0; i < estadoActual.size(); i++) {
                Camion camionActual = estadoActual.get(i);
                

                if (camionActual.puedeAgregarPaquete(paqueteActual)) { 

                    camionActual.agregarPaquete(paqueteActual);

                    int pesoNoAsignado = estadoActual.get(estadoActual.size() -1).getPeso(); // Peso del camión depósito

                    // Poda: solo continúa explorando si el peso no asignado actual no supera el mejor peso no asignado encontrado hasta el momento.
                    if(pesoNoAsignado <= pesoNoAsignadoMin){ 
                    
                        asignar(estadoActual, posPaquete + 1);

                    }

                    camionActual.quitarPaquete(paqueteActual);
                    
                }
            }
        }
       
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public int getPesoNoAsignadoMin() {
        return pesoNoAsignadoMin;
    }

}
