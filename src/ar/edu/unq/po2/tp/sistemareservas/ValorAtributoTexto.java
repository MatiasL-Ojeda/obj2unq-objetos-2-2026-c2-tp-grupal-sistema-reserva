package ar.edu.unq.po2.tp.sistemareservas;

import static ar.edu.unq.po2.tp.sistemareservas.TipoDeAtributo.TEXTO;

public class ValorAtributoTexto extends ValorAtributo{
    private String texto;

    @Override
    public boolean validez(TipoDeAtributo tda){
        return tda == TEXTO;
    }
}
