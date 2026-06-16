/*Un camión está determinado por un ID único, una patente, si está refrigerado o no,
y su capacidad máxima de carga expresada en kilogramos.

Por ejemplo:
Camiones.csv
3
100;AAA000A;1;100
101;AAA001B;0;500
102;AAA002C;1;115
 */

import java.util.ArrayList;
import java.util.List;

public class Camion {
    private int idCamion;
    private String patente;
    private boolean estaRefrigerado;
    private int capacidad;

    private int cargaActual = 0; //contador para llevar la suma de la carga de paquetes
    private List<Paquete>paquetesAsignados = new ArrayList<>(); // lista de paqueetes que se agregan al camion


    public Camion(int idCamion, String patente, boolean estaRefrigerado, int capacidad) {
        this.idCamion = idCamion;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidad = capacidad;
    }

    public boolean puedeCargar(Paquete paquete) {
        // Regla 1: Capacidad
        boolean cabePeso = (this.cargaActual + paquete.getPeso()) <= this.capacidad;
        // Regla 2: Alimentos/Refrigeración
        boolean cumpleFrio = !paquete.isContieneAlimentos() || this.estaRefrigerado; //es verdadera si el paquete NO contiene alimentos o si el camión SÍ está refrigerado.

        return cabePeso && cumpleFrio; //no puede superar el peso y o el paquete no contiene alimento o el camion refrigerado

    }

    public void cargarPaquete(Paquete paquete) {
        paquetesAsignados.add(paquete);
        cargaActual += paquete.getPeso();
    }

    public void descargar(Paquete paquete) {
        paquetesAsignados.remove(paquetesAsignados.size()-1);
        cargaActual -= paquete.getPeso();
    }

    public List<Paquete> getPaquetesAsignados() {
        return new ArrayList<>(paquetesAsignados);
    }



    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isEstaRefrigerado() {
        return estaRefrigerado;
    }

    public void setEstaRefrigerado(boolean estaRefrigerado) {
        this.estaRefrigerado = estaRefrigerado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "idCamion=" + idCamion +
                ", patente='" + patente + '\'' +
                ", refrigerado=" + (estaRefrigerado ? "sí" : "no") +
                ", capacidad=" + capacidad +
                ", cargaActual=" + cargaActual +
                ", paquetes=" + paquetesAsignados +
                '}';
    }





}
