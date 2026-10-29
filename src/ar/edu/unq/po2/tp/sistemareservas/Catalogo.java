package ar.edu.unq.po2.tp.sistemareservas;


public abstract class Catalogo {
    private static int generadorId;// Evita que se repitan ids al ser variable global. (Se aceptan otras soluciones)
    private int id;
    private String nombre;         // Nombre del servicio/paquete
    private String proveedor;      // El proveedor
    private Categoria categoria;   // Categoria del servicio/paquete
    private double precioBase;     // El precio base del servicio/paquete sin el descuento
    private double descuento;      // El porcentaje de descuento.
    private Reserva estadoReserva = new SolicitudPendiente(); // El estado de la reserva.


    public Catalogo(String nombre, String proveedor, Categoria c, double precioBase, double descuento){
        this.id = this.getGeneradorId();
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = c;
        this.precioBase = precioBase;
        this.descuento = descuento;
    }
    private int getGeneradorId (){
        generadorId++;
        return generadorId;
    }
    public double getPrecioBase(){return precioBase;}
    public double precioFinal(){
        return precioBase * ((100 - descuento) / 100);
    }
    public void confirmarReserva(){
        estadoReserva.confirmar(this.estadoReserva, this);
    }
    public void setReserva(Reserva r){this.estadoReserva = r;}
}
