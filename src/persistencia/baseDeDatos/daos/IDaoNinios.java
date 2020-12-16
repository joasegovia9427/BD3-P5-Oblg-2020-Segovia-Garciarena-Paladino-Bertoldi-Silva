package persistencia.baseDeDatos.daos;

import java.util.List;

import logica.Ninio;
import logica.valueObjects.voJuguetes;
import logica.valueObjects.voNinio;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public interface IDaoNinios  {
	
	//public boolean member (int cedula) throws PersistenciaException;
	public boolean member (int cedula, IConexion con) throws PersistenciaException;
	
	//public Ninio find (int cedula) throws PersistenciaException;
	public Ninio find (int cedula, IConexion con) throws PersistenciaException;
	
	//public void insert (Ninio nin) throws PersistenciaException;
	public void insert (Ninio nin, IConexion con) throws PersistenciaException;
	
	//public void delete (int cedula) throws PersistenciaException;
	public void delete(int cedula, IConexion con) throws PersistenciaException;
	
	//public List<voJuguetes> listarNinios() throws PersistenciaException;
	public List <voNinio> listarNinios(IConexion con) throws PersistenciaException;

}
