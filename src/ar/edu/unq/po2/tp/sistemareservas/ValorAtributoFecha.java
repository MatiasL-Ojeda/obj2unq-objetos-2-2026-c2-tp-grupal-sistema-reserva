package ar.edu.unq.po2.tp.sistemareservas;

import java.time.LocalDate;

import static ar.edu.unq.po2.tp.sistemareservas.TipoDeAtributo.FECHA;

public class ValorAtributoFecha extends ValorAtributo{
    private LocalDate fecha;

    @Override
    public boolean validez(TipoDeAtributo tda){
        return tda == FECHA;
    }
}
