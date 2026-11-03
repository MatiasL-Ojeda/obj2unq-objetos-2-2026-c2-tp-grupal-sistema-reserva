package ar.edu.unq.po2.tp.sistemareservas;


import static ar.edu.unq.po2.tp.sistemareservas.TipoDeAtributo.NUMERICO;

public class ValorAtributoNumerico extends ValorAtributo{
    private int numero;

    @Override
    public boolean validez(TipoDeAtributo tda){
        return tda == NUMERICO;
    }
}