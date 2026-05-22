import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorCSV {

    public static Camion[] leerCamiones(String path) {
        Camion[] camiones = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int total = Integer.parseInt(br.readLine().trim());
            camiones = new Camion[total];
            for (int i = 0; i < total; i++) {
                String[] partes = br.readLine().split(";");
                int id = Integer.parseInt(partes[0]);
                String patente = partes[1];
                boolean refrigerado = partes[2].equals("1");
                int capacidad = Integer.parseInt(partes[3]);
                camiones[i] = new Camion(id, patente, refrigerado, capacidad);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer camiones: " + e.getMessage());
        }
        return camiones;
    }

    public static Paquete[] leerPaquetes(String path) {
        Paquete[] paquetes = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int total = Integer.parseInt(br.readLine().trim());
            paquetes = new Paquete[total];
            for (int i = 0; i < total; i++) {
                String[] partes = br.readLine().split(";");
                int id = Integer.parseInt(partes[0]);
                String codigo = partes[1];
                int peso = Integer.parseInt(partes[2]);
                boolean alimentos = partes[3].equals("1");
                int urgencia = Integer.parseInt(partes[4]);
                paquetes[i] = new Paquete(id, codigo, peso, alimentos, urgencia);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer paquetes: " + e.getMessage());
        }
        return paquetes;
    }
    
}
