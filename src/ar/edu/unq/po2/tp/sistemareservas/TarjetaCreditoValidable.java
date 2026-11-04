package ar.edu.unq.po2.tp.sistemareservas;

public interface TarjetaCreditoValidable {
    void validarDatosTarjeta(String nroTarjeta, String anioVto, String cvv);
    void preAutorizacionBanco();
    void transferenciaInmediata();
    void generarVoucher();
}
