import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {

        // Rutas a los archivos CSV 
        String pathCamiones = "Camiones.csv";
        String pathPaquetes = "Paquetes.csv";

        // Inicializar los servicios
        Servicios servicios = new Servicios(pathCamiones, pathPaquetes);

        // Prueba - Servicio 1: Buscar paquete por código
        System.out.println("--- RESULTADOS SERVICIO 1 ---");
        String codigoBuscado = "P001";
        Paquete paqueteEncontrado = servicios.servicio1(codigoBuscado);
        if (paqueteEncontrado != null) {
            System.out.println("Paquete encontrado: " + paqueteEncontrado);
        } else {
            System.out.println("No se encontró el paquete con código: " + codigoBuscado);
        }

        // Prueba - Servicio 2: Buscar paquetes que contienen (o no) alimentos
        System.out.println("\n--- RESULTADOS SERVICIO 2 ---");
        boolean buscarAlimentos = true; // Cambiar a false para probar lo opuesto
        List<Paquete> paquetesAlimentos = servicios.servicio2(buscarAlimentos);
        System.out.println("Paquetes con alimentos (" + buscarAlimentos + "): " + paquetesAlimentos.size());
        for (Paquete p : paquetesAlimentos) {
            System.out.println(p);
        }

        // Prueba - Servicio 3: Buscar paquetes por rango temporal de urgencia
        System.out.println("\n--- RESULTADOS SERVICIO 3 ---");
        int minimo = 10;
        int maximo = 80;
        List<Paquete> paquetesPorUrgencia = servicios.servicio3(minimo, maximo);
        System.out.println("Paquetes con urgencia entre " + minimo + " y " + maximo + ": " + paquetesPorUrgencia.size());
        for (Paquete p : paquetesPorUrgencia) {
            System.out.println(p);
        }

        
        // RESULTADOS BACKTRACKING ---------------------------------------------------------------------------------------------------------
        System.out.println("\n--- RESULTADOS BACKTRACKING ---");

        Backtracking bt = new Backtracking(Arrays.asList(servicios.getPaquetes()),new ArrayList<>());//Arrays.asList() sirve para convertir un arreglo en una List.

        List<Camion> solucion = bt.asignarPaquetes(Arrays.asList(servicios.getCamiones()));

        System.out.println("Solución obtenida:");

        System.out.println("Cantidad de camiones utilizados: " + (solucion.size() - 1)); // se resta el depósito

        for (Camion c : solucion) {

            if (c.getIdCamion() != -1) { // no mostrar el depósito

                System.out.println("\nCamión "
                        + c.getIdCamion()
                        + " (" + c.getPatente() + ")");

                if (c.getPaquetesAsignados().isEmpty()) {
                    System.out.println("   Sin asignaciones");
                } else {

                    for (Paquete p : c.getPaquetesAsignados()) {
                        System.out.println("   "
                                + p.getCodigoPaquete()
                                + " - "
                                + p.getPeso()
                                + " kg");
                    }
                }
            }
        }

        
        System.out.println("\nPeso no asignado: "
                + bt.getPesoNoAsignadoMin()
                + " kg");

        System.out.println("Estados generados: "
                + bt.getEstadosGenerados());

       
        
    }
}
