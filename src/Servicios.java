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
        El CSV recorre una vez el archivo de camiones y una vez el archivo de paquetes
    */

    public Servicios(String pathCamiones, String pathPaquetes){
        this.mapeoContAlimentos=new HashMap<>();
        this.mapeoContAlimentos.put(true, new ArrayList<>());
        this.mapeoContAlimentos.put(false, new ArrayList<>());
        this.indiceUrgencia = new TreeMap<>();
        this.mapeoCodPaquete= new HashMap<>();
        this.camiones = LectorCSV.leerCamiones(pathCamiones);
        this.paquetes = LectorCSV.leerPaquetes(pathPaquetes, mapeoCodPaquete, mapeoContAlimentos, indiceUrgencia);

    }

    /*Expresar la complejidad temporal del servicio 1: O(1)
      Al Hashmap se accede directamente por la clave(codigoPaquete) sin necesidad de recorrer nada
    */
    public Paquete servicio1(String codigoPaquete) {

        return mapeoCodPaquete.get(codigoPaquete);

    }


    /* Expresar la complejidad temporal del servicio 2: O(1)
       Se utiliza un HashMap<Boolean, List<Paquete>>. La lista asociada a la clave true o false se obtiene mediante acceso directo,
       sin recorrer estructuras adicionales.
    */
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return mapeoContAlimentos.get(contieneAlimentos);

    }

    /* Complejidad temporal: O(h + k)
        Donde
        h: altura del TreeMap utilizado para acceder al rango de urgencias.
        k: cantidad de paquetes encontrados dentro de dicho rango.

        Primero se obtiene el subrango de urgencias mediante subMap(), y luego se recorren únicamente los paquetes pertenecientes a ese rango.

        En el peor caso, se devuelven todos los paquetes (k = n), por lo que la complejidad temporal resulta O(n).
    */
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();
        Map<Integer, List<Paquete>> subrango = indiceUrgencia.subMap(urgenciaMinima, true, urgenciaMaxima, true);

        for(List<Paquete> listaporUrgencia : subrango.values()){
            resultado.addAll(listaporUrgencia);
        }
        return resultado;

    }

    //Obtener los camiones y paquetes para el backtracking y greedy
    public Camion[] getCamiones() {
        return camiones;
    }   

    public Paquete[] getPaquetes() {
        return paquetes;
    }

}

