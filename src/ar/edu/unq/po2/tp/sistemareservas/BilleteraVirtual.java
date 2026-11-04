package ar.edu.unq.po2.tp.sistemareservas;

public class BilleteraVirtual extends MetodoPago{
    private String saldo;
    private BilleteraVirtualValidable billeteraVirtual;
    public BilleteraVirtual(float montoAPagar, String saldo) {
        super(montoAPagar);
        this.saldo = saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public void validarDatos(){
        billeteraVirtual.validarSaldo(saldo);
    }
    public void reservarFondos(){
        billeteraVirtual.bloquearSaldo();
    }
    public void ejecutarTransaccion(){
        billeteraVirtual.acreditacion();
    }

    @Override
    public void notificarResultado(Cliente c) {
        super.notificarResultado(c);
        billeteraVirtual.notificacionPush();
    }
}
