package logica;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import persistencia.baseDeDatos.daos.IDaoNinios;
import persistencia.baseDeDatos.daos.daoNinios;
import persistencia.sistemaDeArchivos.poolDeConexiones.PoolConexionesArchivos;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.baseDeDatos.poolDeConexiones.PoolConexiones;
import persistencia.excepciones.PersistenciaException;
import persistencia.factory.IFabricaAbstracta;
import Utilitarios.*;
import logica.IFachada;
import logica.Juguete;
import logica.Ninio;
import logica.excepciones.*;
import logica.interfaces.IPoolConexiones;
import logica.valueObjects.*;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static Fachada instancia;
	private IDaoNinios daoN;
	private IPoolConexiones ipool;
	private SystemProperties sp;
	private IFabricaAbstracta fabrica;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public Fachada() throws RemoteException, PersistenciaException {
		try {
			sp = new SystemProperties();
			String poolConcreto = sp.getpool_className();
			System.out.println(poolConcreto + "*************#########*********");
			ipool = (IPoolConexiones) Class.forName(poolConcreto.trim()).newInstance();

			String nomFab = sp.getFabricaAbstracta();
			fabrica = (IFabricaAbstracta) Class.forName(nomFab.trim()).newInstance();
			daoN = fabrica.crearIDaoNinios();
		} catch (IOException e) {
			throw new PersistenciaException(mensg.errorIO);
		} catch (InstantiationException e) {
			throw new PersistenciaException(mensg.errorPoolCrearIConexion + e.toString());
		} catch (IllegalAccessException e) {
			throw new PersistenciaException(mensg.errorIO);
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException(mensg.errorIO);
		}
	}

	public static Fachada getInstancia() throws RemoteException, PersistenciaException {
		if (instancia == null)
			instancia = new Fachada();
		return instancia;
	}

	public void nuevoNinio(voNinio voN) throws PersistenciaException, LogicaException {
		System.out.println(" ipool.obtenerConexion(true) Dentro de fachada nuevo ninio");
		IConexion icon = ipool.obtenerConexion(true);
		System.out.println("despues de  ipool.obtenerConexion(true) Dentro de fachada nuevo ninio");
		int cedula = voN.getCedula();
		try {
			if (daoN.member(cedula, icon)) {
				//// cuando se llama a la capa de persistencia, hay que encerrar en try para
				//// capturar y liberar
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteNinio);
			} else {
				String apellido = voN.getApellido();
				String nombre = voN.getNombre();
				Ninio nin = new Ninio(cedula, nombre, apellido);
				System.out.println(nin.getNombre());
				daoN.insert(nin, icon);
				ipool.liberarConexion(icon, true);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNuevoNinio);
		}

	}

	public void nuevoJuguete(voJuguetes voj) throws RemoteException, LogicaException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		int cedula = voj.getCedulaNinio();

		try {
			if (daoN.member(cedula, icon)) {

				String descripcion = voj.getDescripcion();

				Ninio auxNinio = daoN.find(cedula, icon);
				int indice = auxNinio.cantJuguetes(icon);
				if (auxNinio.tieneJuguete(indice, icon)) {
					ipool.liberarConexion(icon, false);
					throw new LogicaException(mensg.errorFachadaYaExisteJuguete);

				} else {
					Juguete nuevoJ = new Juguete(descripcion, indice, cedula);
					auxNinio.addJuguete(nuevoJ, icon);
					ipool.liberarConexion(icon, true);
				}

			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteNinio);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNuevoJuguete);
		}

	}

	public List<voNinio> listarNinios() throws RemoteException, PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		List<voNinio> ninios = null;
		try {
			ninios = daoN.listarNinios(icon);
		} catch (Exception e) {
			throw new LogicaException(mensg.errorFachadaListNinios);
		} finally {
			ipool.liberarConexion(icon, true);
		}
		return ninios;
	}

	public List<voJuguetes> listarJuguetes(int cedula) throws PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		List<voJuguetes> jug = null;

		try {
			if (daoN.member(cedula, icon)) {
				Ninio auxNinio = daoN.find(cedula, icon);
				jug = auxNinio.listarjuguetes(icon);
				ipool.liberarConexion(icon, true);
				if (jug.isEmpty()) {
					ipool.liberarConexion(icon, false);
					throw new LogicaException(mensg.errorFachadaNoJuguetesNinio);
				}
			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteNinio);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaListJuguetes);
		}
		return jug;
	}

	public String darDescripcion(int cedula, int num) throws RemoteException, PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		String descripcion = "";

		try {
			if (daoN.member(cedula, icon)) {
				Ninio auxNinio = daoN.find(cedula, icon);

				if (auxNinio.tieneJuguete(num, icon)) {

					descripcion = auxNinio.obtenerJuguete(num, icon).getDescripcion();
					ipool.liberarConexion(icon, true);
				} else {
					ipool.liberarConexion(icon, false);

					throw new LogicaException(mensg.errorFachadaNoJugueteByCINinio);
				}
			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteNinio);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaGetDescripcion);
		}
		return descripcion;
	}

	public void borrarNinioJuguetes(int cedula) throws RemoteException, LogicaException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		try {
			if (daoN.member(cedula, icon)) {
				Ninio auxNinio = daoN.find(cedula, icon);
				// if(auxNinio.tieneJuguete(cedula, icon))//AC� SE ROMPIA POR ESO LO SAQUE
				// {
				auxNinio.borrarJuguetes(icon);
				// }
				daoN.delete(cedula, icon);
				ipool.liberarConexion(icon, true);
			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteNinio);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaDeleteJuguetesByCINinio);
		}
	}

}
