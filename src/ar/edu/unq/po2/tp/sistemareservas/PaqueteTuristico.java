package ar.edu.unq.po2.tp.sistemareservas;

import java.util.LinkedList;
import java.util.List;

public class PaqueteTuristico extends Catalogo{
    private List<Catalogo> contenidoPaquete = new LinkedList<>();
    public PaqueteTuristico(String nombre, String proveedor, Categoria c, double descuento) {
        super(nombre, proveedor, c, 0, descuento);
    }
    @Override
    public double getPrecioBase(){
        return contenidoPaquete.stream().mapToDouble(servicio -> servicio.getPrecioBase()).sum();
    }
    public void agregarServicio(Catalogo c){
        this.contenidoPaquete.add(c);
    }
}
