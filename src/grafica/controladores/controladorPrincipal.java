package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.voJuguetes;
import logica.valueObjects.voNinio;
import persistencia.excepciones.PersistenciaException;

public class controladorPrincipal {

	public IFachada modelo;
//	public ventanaPrincipal vista;
	static SystemProperties sp; 
	public static MensajesPersonalizados msg = new MensajesPersonalizados();
	public controladorPrincipal() throws FileNotFoundException, IOException, NotBoundException {
		sp = new SystemProperties();
		String ip = sp.getIpServidor();
		String puerto = sp.getPuertoServidor();
		String ruta = "//" + ip + ":" + puerto + "/"+ sp.getNombreAPublicar();
		modelo  = (IFachada) Naming.lookup(ruta);			
	}
	
	public void nuevoNinioCP(int cedula, String nombre, String apellido) throws LogicaException, PersistenciaException,  RemoteException
	{
		voNinio voN = new voNinio(cedula, nombre, apellido);
		try {
			modelo.nuevoNinio(voN);
		} catch (LogicaException | PersistenciaException | RemoteException e ) {
			throw new LogicaException(msg.errorFachadaNuevoNinio);
		}
	}
	
	public void nuevoJugueteCP(int numero, String descricion,int  cedula) throws LogicaException, PersistenciaException,  RemoteException
	{
		voJuguetes voj= new voJuguetes(numero, descricion, cedula);
		try {
			modelo.nuevoJuguete(voj);
		} catch (LogicaException | PersistenciaException | RemoteException e ) {
			throw new LogicaException(msg.errorFachadaNuevoJuguete);
		}
	}
	public List<voNinio> listarNinosCP () throws PersistenciaException,  RemoteException, LogicaException
	{
		try {
			return modelo.listarNinios();
		} catch (LogicaException | PersistenciaException | RemoteException e) {
			throw new LogicaException(msg.errorFachadaListNinios);
		}
		
	}
	public List<voJuguetes> listarJuguetesCP (int ced) throws PersistenciaException,  RemoteException, LogicaException
	{
		try {
			return modelo.listarJuguetes(ced);
		} catch (LogicaException | PersistenciaException | RemoteException e) {
			throw new LogicaException(msg.errorFachadaListJuguetes);
			
		}
		
	}
	public String darDescripcionCP(int cedula, int numero) throws PersistenciaException,  LogicaException, RemoteException
	{
		try {
			return modelo.darDescripcion(cedula, numero);
		} catch (LogicaException | PersistenciaException | RemoteException e) {
			throw new LogicaException(msg.errorFachadaGetDescripcion);
		}
	}
	public void borrarNinioJuguetesCP(int cedula) throws LogicaException, PersistenciaException,  RemoteException
	{
		try {
			modelo.borrarNinioJuguetes(cedula);
		} catch (LogicaException | PersistenciaException | RemoteException e) {
			throw new LogicaException(msg.errorFachadaDeleteJuguetesByCINinio);
		}
	}

	
	
}
