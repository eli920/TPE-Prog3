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
    private List<Paquete> paquetesAsignados;
    private int pesoAcumulado;


    public Camion(int idCamion, String patente, boolean estaRefrigerado, int capacidad) {
        this.idCamion = idCamion;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidad = capacidad;
        this.paquetesAsignados = new ArrayList<>();
        this.pesoAcumulado = 0;
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

    public boolean estaRefrigerado() {
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

    public List<Paquete> getPaquetesAsignados() {
        return paquetesAsignados;
    }

    public void agregarPaquete(Paquete paquete) {
        paquetesAsignados.add(paquete);
        pesoAcumulado += paquete.getPeso();
    }

    public void quitarPaquete(Paquete paquete) {
        paquetesAsignados.remove(paquete);
        pesoAcumulado -= paquete.getPeso();
    }

    public int getPeso(){
        return pesoAcumulado;
    }

    //Este método controla que el paquete a agregar no exceda la capacidad del camión y que si el camión no es refrigerado, no se puedan agregar paquetes que contengan alimentos.
    public boolean puedeAgregarPaquete(Paquete paquete) {
        if (!estaRefrigerado() && paquete.contieneAlimentos()) {
            return false; // No se pueden agregar paquetes de alimentos a un camión no refrigerado
        }
        return pesoAcumulado + paquete.getPeso() <= capacidad;
    }

    
}
