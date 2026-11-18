package ar.edu.unq.po2.tp.sistemareservas;
/*
    Un enumerativo que me permite darle un tipo a los atributos dinamicos y si a futuro se
    agregan mas solo hay que agregarlos y definir la clase
 */
public enum TipoDeAtributo {
    TEXTO(String.class),
    NUMERO(Integer.class),       // O Double.class según prefieras
    FECHA(java.time.LocalDate.class),
    BOOLEANO(Boolean.class),
    TRANSPORTE(MetodoTraslado.class);
    private final Class<?> claseEsperada;

    // Constructor del enum
    TipoDeAtributo(Class<?> claseEsperada) {
        this.claseEsperada = claseEsperada;
    }

    public Class<?> getClaseEsperada() {
        return claseEsperada;
    }
}
