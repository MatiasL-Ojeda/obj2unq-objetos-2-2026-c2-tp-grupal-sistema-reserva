package ar.edu.unq.po2.tp.sistemareservas;

public class TarjetaCredito extends MetodoPago{
    private String nroTarjeta;
    private String anioVto;
    private String cvv;
    private TarjetaCreditoValidable banco;
    public TarjetaCredito(float montoAPagar, String nroTarjeta, String anioVto, String cvv){
        super(montoAPagar);
        this.nroTarjeta = nroTarjeta;
        this.anioVto = anioVto;
        this.cvv = cvv;
    }
    public void validarDatos(){
        banco.validarDatosTarjeta(this.nroTarjeta, this.anioVto, this.cvv);
    }
    public void reservarFondos(){
        banco.preAutorizacionBanco();
    }
    public void ejecutarTransaccion(){
        banco.transferenciaInmediata();
    }
}
