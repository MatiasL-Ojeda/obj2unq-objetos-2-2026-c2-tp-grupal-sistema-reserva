package ar.edu.unq.po2.tp.sistemareservas;

public class CriterioNot implements Criterio {
    private Criterio criterio;

    public CriterioNot(Criterio criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean satisface(Catalogo item) {
        return !criterio.satisface(item);
    }
}
