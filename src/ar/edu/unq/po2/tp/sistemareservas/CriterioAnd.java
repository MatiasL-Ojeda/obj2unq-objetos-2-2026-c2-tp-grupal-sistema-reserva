package ar.edu.unq.po2.tp.sistemareservas;

import java.util.ArrayList;
import java.util.List;


public class CriterioAnd implements Criterio {
    private List<Criterio> criterios;

    public CriterioAnd(List<Criterio> criterios) {
        this.criterios = new ArrayList<>(criterios);
    }

    @Override
    public boolean satisface(Catalogo item) {
        return criterios.stream().allMatch(c -> c.satisface(item));
    }
}
