package ar.edu.unq.po2.tp.sistemareservas;

import static ar.edu.unq.po2.tp.sistemareservas.TipoDeAtributo.TRANSPORTE;

public class ValorAtributoTransporte extends ValorAtributo{
        private MetodoTraslado transporte;

        @Override
        public boolean validez(TipoDeAtributo tda){
            return tda == TRANSPORTE;
        }
}
