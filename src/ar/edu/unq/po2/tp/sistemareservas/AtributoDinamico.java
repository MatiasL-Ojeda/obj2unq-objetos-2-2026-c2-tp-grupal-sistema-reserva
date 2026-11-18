package ar.edu.unq.po2.tp.sistemareservas;
/*
    Los atributos dinamicos deben tener nombre, si es obligatorio y su tipo
    por lo que cree esta clase que representa al atributo dinamico
*/
public class AtributoDinamico<T>{
    private final String nombreAtributo;
    private boolean obligatoriedad;
    private final TipoDeAtributo tipo;
    private T valor;

    public AtributoDinamico(String nombreAtributo, boolean obligatoriedad, TipoDeAtributo tipo, T valor){
        this.nombreAtributo = nombreAtributo;
        this.obligatoriedad = obligatoriedad;
        this.tipo = tipo;
        setValor(valor); //Antes de darle valor, se debe comprobar que cumpla los requisitos de obligatoreidad y el tipo esperado
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
    public void setValor(T valor) {
        if (valor != null) {
            // Primero validamos que el objeto pertenezca a la clase esperada por el enum
            if (!tipo.getClaseEsperada().isInstance(valor)) {
                throw new IllegalArgumentException(
                        "El valor provisto no coincide con el tipo esperado para: " + tipo
                );
            }
        }
        //Despues validamos que la obligatoreidad se cumpla
        if (obligatoriedad && valor == null) {
            throw new IllegalArgumentException("El atributo '" + nombreAtributo + "' es obligatorio.");
        }

        this.valor = valor;
    }
    T getValor(){return valor;}
}