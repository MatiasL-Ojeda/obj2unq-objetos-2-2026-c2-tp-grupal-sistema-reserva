package ar.edu.unq.po2.tp.sistemareservas;

public class Servicio {
    int id;
    private String nombre;
    private String proveedor;
    private Categoria categoria;
    private double precioBase;
    private double descuento; // El porcentaje de descuento.

    public Servicio(String nombre, String proveedor, Categoria c, double pb, double descuento){
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = c;
        this.precioBase = pb;
        this.descuento = descuento;
    }

    double precioFinal(){
        return precioBase * ((100 - descuento) / 100);
    }
}
