package cl.duoc.mineria.materiales.exception;

public class MaterialDuplicadoException extends RuntimeException {
    public MaterialDuplicadoException(String mensaje) {
        super(mensaje);
    }
}