package persistencia.excepciones;

public class ClassNotFoundException extends Exception {
	
	public ClassNotFoundException() {}

    public ClassNotFoundException(String mensaje)
    {
       super(mensaje);
    }

}
