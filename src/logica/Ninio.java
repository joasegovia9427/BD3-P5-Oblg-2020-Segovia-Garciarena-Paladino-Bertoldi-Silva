package logica;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import Utilitarios.SystemProperties;
import logica.valueObjects.voJuguetes;
import persistencia.baseDeDatos.daos.IDaoJuguetes;
import persistencia.baseDeDatos.daos.daoJuguetes;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;
import persistencia.factory.IFabricaAbstracta;
import persistencia.sistemaDeArchivos.daos.daoJuguetesArchivos;

public class Ninio implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private int cedula;
	private String nombre;
	private String apellido;
//	private daoJuguetesArchivos secJug;
	private IDaoJuguetes secJug;
	private IFabricaAbstracta fabrica;
	private SystemProperties sp;

	public Ninio(int ced, String nom, String ape) {
		this.cedula = ced;
		this.nombre = nom;
		this.apellido = ape;
		
//		secJug = new daoJuguetesArchivos(ced);   ////FUNCIONA EL INSERT Y LIST DE NIÑO, 
		////ASI QUE EL TEMA NO ESTA EN ARCHIVO SINO EN FABRICA
		
		try {
			sp = new SystemProperties();
			System.out.println("ANTES DEL GET PARAMETRO::");
			String nomFab = sp.getFabricaAbstracta();
			System.out.println("********************************nomFab::"+nomFab.trim());
			fabrica = (IFabricaAbstracta) Class.forName(nomFab.trim()).newInstance();
			System.out.println("PASO el CLASS FOR NAME");
			secJug = (IDaoJuguetes) fabrica.crearIDaoJuguetes(ced);
			System.out.println("paso la creacion del secJug");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getCedula() {
		return this.cedula;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setDescription(int ced) {
		this.cedula = ced;
	}

	public void setNombre(String nom) {
		this.nombre = nom;
	}

	public void setApellidoo(String ape) {
		this.apellido = ape;
	}

	public boolean tieneJuguete(int numJ, IConexion con) throws PersistenciaException {

		boolean tiene = false;
		int i = 0;
		while ((i < secJug.largo(con)) && (!tiene)) {
			if (secJug.kesimo(i, con).getNumero() == numJ)
				tiene = true;
			i++;
		}

		return tiene;

	}


	public int cantJuguetes(IConexion con) throws PersistenciaException {

		return secJug.largo(con);

	}

	void addJuguete(Juguete jug, IConexion con) throws PersistenciaException {

		secJug.insBack(jug, con);

	}

	public Juguete obtenerJuguete(int numJ, IConexion con) throws PersistenciaException {
		boolean obtener = false;
		Juguete j = null;

		int i = 0;
		while ((i < secJug.largo(con)) && (!obtener)) {
			if (secJug.kesimo(i, con).getNumero() == numJ) {
				j = secJug.kesimo(i, con);
				obtener = true;
			}
			i++;

		}

		return j;

	}

	
	public List<voJuguetes> listarjuguetes(IConexion con) throws PersistenciaException {

		return secJug.listarJuguetes(con);
	}

	void borrarJuguetes(IConexion con) throws PersistenciaException {
		secJug.borrarJuguetes(con);
	}

}