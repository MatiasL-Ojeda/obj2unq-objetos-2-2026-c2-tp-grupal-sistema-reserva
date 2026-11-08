package ar.edu.unq.po2.tp.sistemareservas;

public class CriterioPorNombre implements Criterio {
    private final String texto;

    public CriterioPorNombre(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean satisface(Catalogo item) {
        if (item == null || item.getNombre() == null || this.texto == null) {
            return false;
        }
        return item.getNombre().toLowerCase().contains(this.texto.toLowerCase());
    }
}
