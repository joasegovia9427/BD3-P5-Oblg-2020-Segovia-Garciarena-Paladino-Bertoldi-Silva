package persistencia.baseDeDatos.daos;

import java.util.List;

import logica.Juguete;
import logica.valueObjects.voJuguetes;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public interface IDaoJuguetes {
	
	//public void insBack(Juguete in_JugueteNuevo) throws PersistenciaException;
	public void insBack(Juguete in_JugueteNuevo, IConexion con) throws PersistenciaException;
	
	//public int largo() throws PersistenciaException;
	public int largo(IConexion con) throws PersistenciaException;
	
	//public Juguete kesimo(int in_numeroDeJuguete) throws PersistenciaException;
	public Juguete kesimo(int indice,  IConexion con) throws PersistenciaException;
	
	//public List<voJuguetes> listarJuguetes() throws PersistenciaException;
	public List<voJuguetes> listarJuguetes(IConexion con) throws PersistenciaException;
	
	//public void borrarJuguetes()throws PersistenciaException;
	public void borrarJuguetes( IConexion con) throws PersistenciaException;
}
