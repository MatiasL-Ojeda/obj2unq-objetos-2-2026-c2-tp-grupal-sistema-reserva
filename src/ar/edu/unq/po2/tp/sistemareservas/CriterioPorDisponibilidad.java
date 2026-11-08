package ar.edu.unq.po2.tp.sistemareservas;

public class CriterioPorDisponibilidad implements Criterio {

    @Override
    public boolean satisface(Catalogo item) {
        return item.tieneDisponibilidad();
    }
}
