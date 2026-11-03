package ar.edu.unq.po2.tp.sistemareservas;
/*
    Aca convergen las clases de atributodinamico y valor atributo y se comprueba
    que el tipo del atributo dianamico sea consistente con su valor
*/
public class AtributoDefinido {
    private AtributoDinamico ad;
    private ValorAtributo va;

    public boolean esValido() {
        // (a) Comprueba que si el atributo es obligatorio, entonces no este vacio
        if (ad.getObligatoriedad() && (va == null || va.toString().isEmpty())) {
            return false;
        }
        // (b) Si es obligatorio, entonces el tipo y el valor deben ser consistentes.
        if (va != null) {
            return va.validez(ad.getTipo());
        }
        // Si no es nada anterior, entonces debe ser no obligatorio y es verdadero automaticamente
        return true;
    }
}
