import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Servicios {
    private Camion[] camiones;
    private Paquete[] paquetes;
    private Map<String, Paquete> mapeoCodPaquete;
    private Map<Boolean, List<Paquete>> mapeoContAlimentos;
    private TreeMap<Integer, List<Paquete>>indiceUrgencia;
    
    
    /*Expresar la complejidad temporal del constructor: O(n + m)
        donde n = cantidad de camiones y m = cantidad de paquetes. 
        El CSV recorre una vez el archivo de camiones y una vez el archivo de paquetes*/

    public Servicios(String pathCamiones, String pathPaquetes){
        this.camiones = LectorCSV.leerCamiones(pathCamiones);
        this.paquetes = LectorCSV.leerPaquetes(pathPaquetes);
        this.mapeoCodPaquete= new HashMap<>();
        this.mapeoContAlimentos=new HashMap<>();
        this.mapeoContAlimentos.put(true, new ArrayList<>());
        this.mapeoContAlimentos.put(false, new ArrayList<>());
        this.indiceUrgencia = new TreeMap<>();

        for (Paquete p : paquetes) {
            // Servicio 1
            mapeoCodPaquete.put(p.getCodigoPaquete(), p);
            // Servicio 2
            mapeoContAlimentos.get(p.isContieneAlimentos()).add(p);
            // Servicio 3
            int urgencia = p.getNivelUrgencia();
            if (!indiceUrgencia.containsKey(urgencia)) {
                indiceUrgencia.put(urgencia, new ArrayList<>());
            }
            indiceUrgencia.get(urgencia).add(p);
        }

    }

    /*Expresar la complejidad temporal del servicio 1: O(1)
      Al Hashmap se accede directamente por la clave(codigoPaquete) sin necesidad de recorrer nada*/
    public Paquete servicio1(String codigoPaquete) {

        return mapeoCodPaquete.get(codigoPaquete);

    }

    /*Expresar la complejidad temporal del servicio 2.*/
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return mapeoContAlimentos.get(contieneAlimentos);

    }

    /*Expresar la complejidad temporal del servicio 3.*/
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();
        Map<Integer, List<Paquete>> subrango = indiceUrgencia.subMap(urgenciaMinima, true, urgenciaMaxima, true);

        for(List<Paquete> listaporUrgencia : subrango.values()){
            resultado.addAll(listaporUrgencia);
        }
        return resultado;

    }

}

