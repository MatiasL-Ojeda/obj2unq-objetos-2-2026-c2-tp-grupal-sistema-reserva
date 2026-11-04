package ar.edu.unq.po2.tp.sistemareservas;

public interface TransferenciaBancariaValidable {
    void validarDatosTransferencia(String cvu, String alias);
    void transferenciaInmediata();
    void generarComprobante();
}
