package ar.edu.unq.po2.tp.sistemareservas;

public class CriterioPorCategoria implements Criterio {
    private Categoria categoria;

    public CriterioPorCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean satisface(Catalogo item) {
        return item.getCategoria() == categoria;
    }
}
