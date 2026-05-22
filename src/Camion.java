/*Un camión está determinado por un ID único, una patente, si está refrigerado o no,
y su capacidad máxima de carga expresada en kilogramos.

Por ejemplo:
Camiones.csv
3
100;AAA000A;1;100
101;AAA001B;0;500
102;AAA002C;1;115
 */

public class Camion {
    private int idCamion;
    private String patente;
    private boolean estaRefrigerado;
    private int capacidad;


    public Camion(int idCamion, String patente, boolean estaRefrigerado, int capacidad) {
        this.idCamion = idCamion;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidad = capacidad;
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

    

    
}
