package ar.edu.unq.po2.tp.sistemareservas;

import java.util.ArrayList;
import java.util.List;

public class CriterioOr implements Criterio {
    private final List<Criterio> criterios;

    public CriterioOr(List<Criterio> criterios) {
        this.criterios = new ArrayList<>(criterios);
    }

    @Override
    public boolean satisface(Catalogo item) {
        return criterios.stream().anyMatch(c -> c.satisface(item));
    }
}
