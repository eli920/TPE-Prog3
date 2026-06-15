import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Rutas a los archivos CSV (ajustar según corresponda en tu proyecto)
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

        // --- SEGUNDA PARTE: ASIGNACIÓN DE PAQUETES ---
        List<Camion> camiones = servicios.getCamiones();
        List<Paquete> paquetes = servicios.getPaquetes();

        AsignadorPaquetes asignador = new AsignadorPaquetes();


        System.out.println("\n--- EJECUTANDO BACKTRACKING ---");
// Asegúrate de que el método imprima los resultados en el formato pedido
        asignador.asignarPaquetes(paquetes, camiones);


    }
}
