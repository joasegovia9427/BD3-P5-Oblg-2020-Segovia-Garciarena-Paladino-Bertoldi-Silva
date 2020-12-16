package logica.interfaces;

import persistencia.baseDeDatos.poolDeConexiones.*;
				
public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean modifica);
	
    public void liberarConexion(IConexion con, boolean ok);
		
		
	
	
}
