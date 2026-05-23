import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servicios {
    private Camion[] camiones;
    private Paquete[] paquetes;
    private Map<String, Paquete> mapeoCodPaquete;
    private Map<Boolean, List<Paquete>> mapeoContAlimentos;
    
    
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

        for (Paquete p : paquetes) {
            mapeoCodPaquete.put(p.getCodigoPaquete(), p);
            mapeoContAlimentos.get(p.isContieneAlimentos()).add(p);
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
        return new ArrayList<>();

    }

}

