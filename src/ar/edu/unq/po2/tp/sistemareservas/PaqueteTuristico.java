package ar.edu.unq.po2.tp.sistemareservas;

import java.util.LinkedList;
import java.util.List;

public class PaqueteTuristico extends Catalogo{
    private final List<Catalogo> contenidoPaquete = new LinkedList<>();
    public PaqueteTuristico(String nombre, String proveedor, Categoria c, double descuento) {
        super(nombre, proveedor, c, 0, descuento);
    }
    @Override
    public double getPrecioBase(){
        return contenidoPaquete.stream().mapToDouble(Catalogo::getPrecioBase).sum();
    }
    public void addCatalogo(Catalogo c){
        this.contenidoPaquete.add(c);
    }
}
