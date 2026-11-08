package ar.edu.unq.po2.tp.sistemareservas;

public class CriterioPorPrecioMaximo implements Criterio {
    private final double precioMaximo;

    public CriterioPorPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    @Override
    public boolean satisface(Catalogo item) {
        return item.getPrecioBase() <= precioMaximo;
    }
}
