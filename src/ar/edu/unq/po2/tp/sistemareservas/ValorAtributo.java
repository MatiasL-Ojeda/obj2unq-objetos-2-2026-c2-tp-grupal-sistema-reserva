package ar.edu.unq.po2.tp.sistemareservas;

public abstract class ValorAtributo {
    /*
    Clase abstracta que guarda los diferentes tipos de valores de atributo dinamico y comprueba
    su validez, lo que permite a futuro agregar otros tipos de atributos dinamicos
     */
    public abstract boolean validez(TipoDeAtributo tda);
}
