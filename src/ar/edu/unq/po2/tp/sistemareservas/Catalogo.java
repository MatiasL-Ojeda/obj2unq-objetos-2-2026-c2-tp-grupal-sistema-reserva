package ar.edu.unq.po2.tp.sistemareservas;


import java.util.LinkedList;
import java.util.List;

public abstract class Catalogo {
    private static int generadorId;// Evita que se repitan ids al ser variable global. (Se aceptan otras soluciones)
    private int codigo;
    private String nombre;         // Nombre del servicio/paquete
    private String proveedor;      // El proveedor
    private Categoria categoria;   // Categoria del servicio/paquete
    private double precioBase;     // El precio base del servicio/paquete sin el descuento
    private double descuento;      // El porcentaje de descuento.
    private List<AtributoDefinido> atributosDefinidos = new LinkedList<>(); // Lista de atributos dinamicos definidos.

    public Catalogo(String nombre, String proveedor, Categoria c, double precioBase, double descuento){
        this.codigo = this.getGeneradorId();
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = c;
        this.precioBase = precioBase;
        this.descuento = descuento;
    }
    //Generador de ids que se modifica cada que se crea un nuevo servicio/paquete
    private int getGeneradorId (){
        generadorId++;
        return generadorId;
    }
    public double getPrecioBase(){return precioBase;}
    public double precioFinal(){
        return this.getPrecioBase() * (100 - descuento) / 100;
    }

    //Valida todos los atributos dinamicos y fijos obligatorios
    public boolean validar(){
        // El codigo no puede ser menor a 1, por lo que igualarlo a 0 comprueba que no se le dio un valor.
        if(codigo == 0){
            return false;
        }
        //Comprueba que el nombre no sea nulo o una cadena de espacios.
        if(nombre == null || nombre.trim().isEmpty()){
            return false;
        }
        //Verifica la validez de todos los atributos dinamicos definidos.
        return atributosDefinidos.stream().allMatch(AtributoDefinido::esValido);
    }
    public void addCatalogo(Catalogo c){}
}
