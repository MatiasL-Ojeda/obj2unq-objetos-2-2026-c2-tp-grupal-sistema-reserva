package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MetodoPagoTest {

    private void setField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void billeteraVirtualFlujoCompleto() {
        BilleteraVirtualValidable mockBilletera = mock(BilleteraVirtualValidable.class);
        SistemaCatalogo mockSistema = mock(SistemaCatalogo.class);

        BilleteraVirtual bv = new BilleteraVirtual(1000.0f, "5000");
        setField(bv, "billeteraVirtual", mockBilletera);

        bv.validarDatos();
        verify(mockBilletera).validarSaldo("5000");

        bv.setSaldo("3000");
        bv.validarDatos();
        verify(mockBilletera).validarSaldo("3000");

        bv.reservarFondos();
        verify(mockBilletera).bloquearSaldo();

        bv.ejecutarTransaccion();
        verify(mockBilletera).acreditacion();

        bv.notificarResultado(mockSistema);
        verify(mockSistema).confirmarReserva();
        verify(mockBilletera).notificacionPush();
    }

    @Test
    void tarjetaCreditoFlujoCompleto() {
        TarjetaCreditoValidable mockBanco = mock(TarjetaCreditoValidable.class);
        SistemaCatalogo mockSistema = mock(SistemaCatalogo.class);

        TarjetaCredito tc = new TarjetaCredito(2500.0f, "1234567890123456", "12/28", "123");
        setField(tc, "banco", mockBanco);

        tc.validarDatos();
        verify(mockBanco).validarDatosTarjeta("1234567890123456", "12/28", "123");

        tc.reservarFondos();
        verify(mockBanco).preAutorizacionBanco();

        tc.ejecutarTransaccion();
        verify(mockBanco).transferenciaInmediata();

        tc.notificarResultado(mockSistema);
        verify(mockSistema).confirmarReserva();
    }

    @Test
    void transferenciaBancariaFlujoCompleto() {
        TransferenciaBancariaValidable mockCuenta = mock(TransferenciaBancariaValidable.class);
        SistemaCatalogo mockSistema = mock(SistemaCatalogo.class);

        TransferenciaBancaria tb = new TransferenciaBancaria(1500.0f, "000000310001000", "alias.banco");
        setField(tb, "cuentaDigital", mockCuenta);

        tb.validarDatos();
        verify(mockCuenta).validarDatosTransferencia("000000310001000", "alias.banco");

        tb.reservarFondos(); // método con cuerpo vacío

        tb.ejecutarTransaccion();
        verify(mockCuenta).transferenciaInmediata();

        tb.notificarResultado(mockSistema);
        verify(mockSistema).confirmarReserva();
    }

    @Test
    void metodoPagoBaseNotificarResultado() {
        SistemaCatalogo mockSistema = mock(SistemaCatalogo.class);

        MetodoPago metodo = new MetodoPago(500.0f) {
            @Override
            public void validarDatos() {}
            @Override
            public void reservarFondos() {}
            @Override
            public void ejecutarTransaccion() {}
        };

        metodo.notificarResultado(mockSistema);
        verify(mockSistema).confirmarReserva();
    }
}
