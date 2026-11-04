package ar.edu.unq.po2.tp.sistemareservas;

public class TransferenciaBancaria extends MetodoPago{
    private String cvu;
    private String alias;
    private TransferenciaBancariaValidable cuentaDigital;
    public TransferenciaBancaria(float montoAPagar, String cvu, String alias){
        super(montoAPagar);
        this.cvu = cvu;
        this.alias = alias;
    }

    public void validarDatos(){
        cuentaDigital.validarDatosTransferencia(cvu, alias);
    }
    public void reservarFondos(){}
    public void ejecutarTransaccion(){
        cuentaDigital.transferenciaInmediata();
    }
}
