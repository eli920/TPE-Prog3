/*Un paquete está determinado por un ID único, un código identificador, su peso en
kilogramos, si contiene alimentos o no, y un nivel de urgencia (valor entero entre 1 y
100).
Paquetes.csv
4
1;P001;30;1;80
2;P002;100;0;2
3;P003;80;0;10
4;P004;25;1;100 */

public class Paquete {
    private int idPaquete;
    private String codigoPaquete;
    private int peso;
    private boolean contieneAlimentos;
    private int nivelUrgencia;


    public Paquete(int idPaquete, String codigoPaquete, int peso, boolean contieneAlimentos, int nivelUrgencia) {
        this.idPaquete = idPaquete;
        this.codigoPaquete = codigoPaquete;
        this.peso = peso;
        this.contieneAlimentos = contieneAlimentos;
        setNivelUrgencia(nivelUrgencia);
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isContieneAlimentos() {
        return contieneAlimentos;
    }

    public void setContieneAlimentos(boolean contieneAlimentos) {
        this.contieneAlimentos = contieneAlimentos;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(int nivelUrgencia) {

        if (nivelUrgencia < 1 || nivelUrgencia > 100) {
            throw new IllegalArgumentException("Nivel de urgencia debe estar entre 1 y 100");
        }
        this.nivelUrgencia = nivelUrgencia;
    }

    @Override
    public String toString(){
        return "Paquete{" +
                "idPaquete=" + idPaquete +
                ", codigoPaquete='" + codigoPaquete + '\'' +
                ", peso=" + peso +
                ", contieneAlimentos=" + contieneAlimentos +
                ", nivelUrgencia=" + nivelUrgencia +
                '}';
    }

    
    
}
