package logica.excepciones;

public class ServidorException extends Exception {
	public ServidorException() {}

    public ServidorException(String mensaje)
    {
       super(mensaje);
    }
}
