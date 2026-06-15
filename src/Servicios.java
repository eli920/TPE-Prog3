import java.util.*;

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
        this.mapeoContAlimentos=new HashMap<>();
        this.mapeoContAlimentos.put(true, new ArrayList<>());
        this.mapeoContAlimentos.put(false, new ArrayList<>());
        this.indiceUrgencia = new TreeMap<>();
        this.mapeoCodPaquete= new HashMap<>();
        this.camiones = LectorCSV.leerCamiones(pathCamiones);
        this.paquetes = LectorCSV.leerPaquetes(pathPaquetes, mapeoCodPaquete, mapeoContAlimentos, indiceUrgencia);

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


    public List<Camion> getCamiones() {
        if (camiones == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(camiones));
    }


    public List<Paquete> getPaquetes() {
        if (paquetes == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(paquetes));
    }
}

