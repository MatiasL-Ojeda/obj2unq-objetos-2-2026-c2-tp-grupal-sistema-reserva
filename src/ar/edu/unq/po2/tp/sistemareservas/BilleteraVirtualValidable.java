package ar.edu.unq.po2.tp.sistemareservas;

public interface BilleteraVirtualValidable {
    void validarSaldo(String saldo);
    void bloquearSaldo();
    void acreditacion();
    void notificacionPush();
}
