package persistencia.excepciones;

public class PersistenciaException extends Exception{


	public PersistenciaException() {}

      public PersistenciaException(String mensaje)
      {
         super(mensaje);
      }

}
