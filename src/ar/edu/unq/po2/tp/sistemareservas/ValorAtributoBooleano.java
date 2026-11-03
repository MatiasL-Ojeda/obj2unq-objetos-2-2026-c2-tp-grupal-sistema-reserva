package ar.edu.unq.po2.tp.sistemareservas;

import static ar.edu.unq.po2.tp.sistemareservas.TipoDeAtributo.BOOLEANO;

public class ValorAtributoBooleano extends ValorAtributo{
    private boolean valorDeVerdad;

    @Override
    public boolean validez(TipoDeAtributo tda){
        return tda == BOOLEANO;
    }
}
