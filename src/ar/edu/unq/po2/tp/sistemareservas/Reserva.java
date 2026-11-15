package ar.edu.unq.po2.tp.sistemareservas;

public abstract class Reserva {
    public void confirmar(Reserva r, SistemaCatalogo c){
        throw new IllegalStateException("No se puede realizar esa accion en este momento.");
    }
    public void checkear(Reserva r , SistemaCatalogo c){
        throw new IllegalStateException("No se puede realizar esa accion en este momento.");
    }
    public void comenzar(Reserva r , SistemaCatalogo c){
        throw new IllegalStateException("No se puede realizar esa accion en este momento.");
    }
    public void finalizar(Reserva r, SistemaCatalogo c){
        throw new IllegalStateException("No se puede realizar esa accion en este momento.");
    }
    public void cancelar(Reserva r , SistemaCatalogo c){
        throw new IllegalStateException("No se puede realizar esa accion en este momento.");
    }
    //Solamente se puede agregar servicios cuando el estado es Solicitud Pendiente, cualquier otro arroja una excepcion.
    public void agregarServicio(Catalogo catalogoCliente, Catalogo servicioAAgregar){
        throw new IllegalStateException("Ya no se puede agregar servicios");
    }
}
