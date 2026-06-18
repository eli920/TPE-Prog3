
import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {

        String pathCamiones = "Camiones.csv";
        String pathPaquetes = "Paquetes.csv";

        Servicios servicios = new Servicios(pathCamiones, pathPaquetes);

        mostrarServicios(servicios);

        ejecutarBacktracking(servicios);

        ejecutarGreedy(servicios);
    }

    private static void mostrarServicios(Servicios servicios) {

        System.out.println("--- RESULTADOS SERVICIO 1 ---");
        String codigoBuscado = "P001";
        Paquete paqueteEncontrado = servicios.servicio1(codigoBuscado);

        if (paqueteEncontrado != null) {
            System.out.println("Paquete encontrado: " + paqueteEncontrado);
        } else {
            System.out.println("No se encontró el paquete con código: " + codigoBuscado);
        }

        System.out.println("\n--- RESULTADOS SERVICIO 2 ---");
        boolean buscarAlimentos = true;
        List<Paquete> paquetesAlimentos = servicios.servicio2(buscarAlimentos);
        System.out.println("Paquetes con alimentos (" + buscarAlimentos + "): " + paquetesAlimentos.size());

        for (Paquete p : paquetesAlimentos) {
            System.out.println(p);
        }

        System.out.println("\n--- RESULTADOS SERVICIO 3 ---");
        int minimo = 10;
        int maximo = 80;
        List<Paquete> paquetesPorUrgencia = servicios.servicio3(minimo, maximo);
        System.out.println("Paquetes con urgencia entre " + minimo + " y " + maximo + ": " + paquetesPorUrgencia.size());

        for (Paquete p : paquetesPorUrgencia) {
            System.out.println(p);
        }
    }

    private static void ejecutarBacktracking(Servicios servicios) {

        System.out.println("\n--- RESULTADOS BACKTRACKING ---");

        Backtracking bt = new Backtracking(Arrays.asList(servicios.getPaquetes()));

        List<Camion> solucion =
                bt.asignarPaquetes(Arrays.asList(servicios.getCamiones()));

        System.out.println("Solución obtenida:");

        mostrarCamiones(solucion, true);

        System.out.println("\nPeso no asignado: "
                + bt.getPesoNoAsignadoMin() + " kg");

        System.out.println("Estados generados: "
                + bt.getEstadosGenerados());
    }

    private static void ejecutarGreedy(Servicios servicios) {

        System.out.println("\n--- RESULTADOS GREEDY ---");

        Greedy greedy = new Greedy(Arrays.asList(servicios.getPaquetes()));

        List<Camion> solucionGreedy =
                greedy.asignarPaquetes(Arrays.asList(servicios.getCamiones()));

        System.out.println("Solución obtenida:");

        mostrarCamiones(solucionGreedy, false);

        System.out.println("\nPeso no asignado: "
                + greedy.getPesoNoAsignado() + " kg");

        System.out.println("Candidatos considerados: "
                + greedy.getEstadosGenerados());
    }

    private static void mostrarCamiones(List<Camion> camiones, boolean ignorarDeposito) {

        for (Camion c : camiones) {

            if (ignorarDeposito && c.getIdCamion() == -1) {
                continue;
            }

            System.out.println("\nCamión "
                    + c.getIdCamion()
                    + " (Patente: " + c.getPatente()
                    + " - Capacidad: " + c.getCapacidad() + " kg"
                    + (c.estaRefrigerado() ? " - Refrigerado" : "")
                    + ")");

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

    
}
