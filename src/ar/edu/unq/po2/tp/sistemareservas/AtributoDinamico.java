package ar.edu.unq.po2.tp.sistemareservas;
/*
    Los atributos dinamicos deben tener nombre, si es obligatorio y su tipo
    por lo que cree esta clase que representa al atributo dinamico
*/
public class AtributoDinamico {
    private final String nombreAtributo;
    private boolean obligatoriedad;
    private final TipoDeAtributo tipo;

    public AtributoDinamico(String nombreAtributo, boolean obligatoriedad, TipoDeAtributo tipo){
        this.nombreAtributo = nombreAtributo;
        this.obligatoriedad = obligatoriedad;
        this.tipo = tipo;
    }

    public String getNombreAtributo() {
        return nombreAtributo;
    }
    public void setObligatoriedad(boolean obligatoriedad){this.obligatoriedad = obligatoriedad;}
    public boolean getObligatoriedad(){
        return obligatoriedad;
    }

    public TipoDeAtributo getTipo() {
        return tipo;
    }
}