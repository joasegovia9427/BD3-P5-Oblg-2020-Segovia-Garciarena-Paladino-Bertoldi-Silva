package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import logica.excepciones.*;
import logica.valueObjects.*;
import persistencia.excepciones.PersistenciaException;

public interface IFachada extends Remote{
	
	/** M�todo nuevoNinio que registra un nuevo ninio
	* @param nuevo recibe voNinio con los datos del ninio
	* @throws LogicaException, PersistenciaException
	 * @throws RemoteException */
	public void nuevoNinio (voNinio voN) throws LogicaException, PersistenciaException, RemoteException;
	
	/** M�todo nuevoJuguete que registra un nuevo juguete
	* @param nuevo recibe voJuguetes con los datos del juguete
	* @throws LogicaException, PersistenciaException
	 * @throws RemoteException */
	public void nuevoJuguete (voJuguetes voj) throws LogicaException, PersistenciaException, RemoteException;
	
	/** M�todo listarNinos que devuelve todos los ninios registrados
	* @return retorna una List de voNinio
	* @throws PersistenciaException
	 * @throws RemoteException 
	 * @throws LogicaException */
	public List<voNinio> listarNinios () throws PersistenciaException, RemoteException, LogicaException;
	
	/** M�todo listarNinos que devuelve todos los juguetes de un ninio
	* @return retorna una List de voJuguetes
	* @throws PersistenciaException
	 * @throws RemoteException 
	 * @throws LogicaException */
	public List<voJuguetes> listarJuguetes (int ced) throws PersistenciaException, RemoteException, LogicaException;
	
	/** M�todo darDescripcion que devuelve la descripcion del juguete
	* @return retorna un String con la descripcion
	* @throws PersistenciaException
	 * @throws LogicaException 
	 * @throws RemoteException */
	public String darDescripcion (int ced, int num) throws PersistenciaException, LogicaException, RemoteException;
	
	/** M�todo borrarNinioJuguetes elimina del sistema al ni�o con la c�dula ingresada, y
		tambi�n elimina a todos sus juguetes
	* @throws PersistenciaException, LogicaException
	 * @throws RemoteException */
	public void borrarNinioJuguetes (int ced) throws LogicaException, PersistenciaException, RemoteException;
	

}
