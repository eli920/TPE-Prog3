public class App {
    public static void main(String[] args) throws Exception {

        Servicios servicio = new Servicios("Camiones.csv", "Paquetes.csv");
       
        // Para verificar que se cargó bien
        // Camion[] camiones = LectorCSV.leerCamiones("Camiones.csv");
        // Paquete[] paquetes = LectorCSV.leerPaquetes("Paquetes.csv");

        // for (Camion c : camiones) {
        //     System.out.println(c.getIdCamion() + " " + c.getPatente());
        // }

        // for (Paquete p : paquetes) {
        //     System.out.println(p.getIdPaquete() + " " + p.getCodigoPaquete());
        // }
    }
}
