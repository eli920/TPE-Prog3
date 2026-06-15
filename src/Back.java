//import java.util.ArrayList;
//import java.util.List;
//
//public class Back {
//    private List<Paquete> paquetes;
//    private List<Camion> solucion;
//    private int pesoLibreMin; // max value
//    private int mejorPesoNoAsignado;
//    private int totalPeso;
//    private int estadosGenerados;
//
//    public Back() {
//        this.solucion = new ArrayList<>();
//        this.mejorPesoNoAsignado = Integer.MAX_VALUE;
//        this.estadosGenerados = 0;
//    }
//
//    public List<Camion> asignarPaquetes(List<Camion> camiones, List<Paquete> paquetes){
//        this.paquetes = paquetes;
//        this.totalPeso = paquetes.stream().mapToInt(Paquete::getPeso).sum();
//        this.mejorPesoNoAsignado = Integer.MAX_VALUE;
//        this.solucion.clear();
//        this.estadosGenerados = 0;
//
//        asignar(0, camiones );
//
//        return solucion;
//    }
//
//
//
//    private void asignar(int posPaquete, List<Camion> camiones){
//        if (posPaquete == paquetes.size()){
//            int pesoLibre = camiones.get(camiones.size()-1).getCapacidad();
//            if (pesoLibre < pesoLibreMin) {
//                pesoLibreMin = pesoLibre;
//                solucion.clear();
//                solucion.addAll(camiones);
//            }
//        }
//        else {
//            Paquete p = paquetes.get(posPaquete);
//
//            for (Camion c : camiones) {
//                if (c.puedeCargar(p)){
//                    c.cargarPaquete(p);
//
//                    asignar(posPaquete + 1, camiones);
//
//                    c.descargar(p);
//                }
//            }
//        }
//    }
//
//
//    private void guardarCopia(List<Camion> camiones) {
//        solucion.clear();
//        for (Camion c : camiones) {
//            Camion copia = new Camion(c.getIdCamion(), c.getPatente(), c.isEstaRefrigerado(), c.getCapacidad());
//            for (Paquete p : c.getPaquetesAsignados()) {
//                copia.cargarPaquete(p);
//            }
//            solucion.add(copia);
//        }
//    }
//
//    private List<Camion> copiaMejorSolucion() {
//        // devolver copias para evitar aliasing
//        List<Camion> resultado = new ArrayList<>();
//        for (Camion c : solucion) {
//            Camion copia = new Camion(c.getIdCamion(), c.getPatente(), c.isEstaRefrigerado(), c.getCapacidad());
//            for (Paquete p : c.getPaquetesAsignados()) copia.cargarPaquete(p);
//            resultado.add(copia);
//        }
//        return resultado;
//    }
//
//    public int getMejorPesoNoAsignado() {
//        return mejorPesoNoAsignado;
//    }
//
//    public int getEstadosGenerados() {
//        return estadosGenerados;
//    }
//}
