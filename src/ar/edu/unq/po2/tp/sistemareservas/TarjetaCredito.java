package ar.edu.unq.po2.tp.sistemareservas;

public class TarjetaCredito extends MetodoPago implements CreditCardPayable{
    private String nroTarjeta;
    private String anioVto;
    private String cvv;

    public TarjetaCredito(float montoAPagar, String nroTarjeta, String anioVto, String cvv){
        super(montoAPagar);
        this.nroTarjeta = nroTarjeta;
        this.anioVto = anioVto;
        this.cvv = cvv;
    }
    public void validarDatos(){}
    public void reservarFondos(){}
    public void ejecutarTransaccion(){}
    public void notificarResultado(){}
}
