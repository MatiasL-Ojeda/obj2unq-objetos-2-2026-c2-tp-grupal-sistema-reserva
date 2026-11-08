package ar.edu.unq.po2.tp.sistemareservas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorDeCatalogo {
    private final List<Catalogo> items;

    public BuscadorDeCatalogo() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Catalogo item) {
        this.items.add(item);
    }

    public List<Catalogo> buscar(Criterio criterio) {
        if (criterio == null) {
            return new ArrayList<>();
        }
        return this.items.stream()
                .filter(criterio::satisface)
                .collect(Collectors.toList());
    }
}
