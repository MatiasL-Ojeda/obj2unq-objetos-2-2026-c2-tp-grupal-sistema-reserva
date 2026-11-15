package ar.edu.unq.po2.tp.sistemareservas;

public class Cliente {
    private final MetodoPago metodoPago;
    private final String correo;

    public Cliente (MetodoPago mP, String c) {
        this.correo = c;
        this.metodoPago = mP;
    }

    public String Correo () {return correo;}
    public MetodoPago metodoPago () {return metodoPago;}
}
