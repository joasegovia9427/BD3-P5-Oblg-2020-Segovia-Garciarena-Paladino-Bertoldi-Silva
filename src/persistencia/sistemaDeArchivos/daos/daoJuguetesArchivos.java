package persistencia.sistemaDeArchivos.daos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import logica.Juguete;
import logica.Ninio;
import logica.valueObjects.voJuguetes;
import logica.valueObjects.voNinio;
import persistencia.baseDeDatos.daos.IDaoJuguetes;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class daoJuguetesArchivos implements IDaoJuguetes, Serializable {

	private static final long serialVersionUID = 1L;
	private int cedulaNinio;
	private SystemProperties sp;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	private String nomArch = "";// sp.getNombreDirectorioArchivos()+"juguetes-";

	public daoJuguetesArchivos(int in_CedulaNinio) {
		this.cedulaNinio = in_CedulaNinio;
		try {
			sp = new SystemProperties();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insBack(Juguete in_JugueteNuevo, IConexion con) throws PersistenciaException {
		Integer ced = in_JugueteNuevo.getCedulaNinio();
		String cedula = ced.toString();

		nomArch = sp.getNombreDirectorioArchivos() + "juguetes-" + cedula + ".txt";

		File fl = new File(nomArch);
		try {
			if (fl.exists())// si existe
			{
				// Si existe el archivo Juguete-Cedula.text, entonces traigo y recupero los
				// juguetes guardados en memoria
				ArrayList<Juguete> Juguetes = daoJuguetesArchivos.Leer(cedula);
				Juguetes.add(in_JugueteNuevo);
				daoJuguetesArchivos.guardarArrayListJuguetes(Juguetes, cedula);

			} else {
				ArrayList<Juguete> JugueteNuevo = new ArrayList<Juguete>();
				JugueteNuevo.add(in_JugueteNuevo);
				daoJuguetesArchivos.guardarArrayListJuguetes(JugueteNuevo, cedula);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int largo(IConexion con) throws PersistenciaException {

		int cant = 0;
		Integer ced = this.cedulaNinio;
		String cedula = ced.toString();
		try {
			ArrayList<Juguete> Juguetes = daoJuguetesArchivos.Leer(cedula);
			cant = Juguetes.size();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cant;
	}

	public Juguete kesimo(int indice, IConexion con) throws PersistenciaException {
		Juguete j = null;
		Integer ced = this.cedulaNinio;
		String cedula = ced.toString();
		try {
			ArrayList<Juguete> Juguetes = daoJuguetesArchivos.Leer(cedula);
			j = Juguetes.get(indice);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return j;
	}

	public List<voJuguetes> listarJuguetes(IConexion con) throws PersistenciaException {
		List<voJuguetes> listaDevoJuguetes = new ArrayList<voJuguetes>();
		try {
			Integer ced = this.cedulaNinio;
			String cedula = ced.toString();
			ArrayList<Juguete> Juguetes = daoJuguetesArchivos.Leer(cedula);

			for (Juguete jugu : Juguetes) {
				voJuguetes vo = new voJuguetes(jugu.getNumero(), jugu.getDescripcion(), jugu.getCedulaNinio());
				listaDevoJuguetes.add(vo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaDevoJuguetes;
	}

	public void borrarJuguetes(IConexion con) throws PersistenciaException {
		Integer ced = this.cedulaNinio;
		String cedula = ced.toString();
		String aEliminar = sp.getNombreDirectorioArchivos() + "juguetes-" + cedula + ".txt";
		File file = new File(aEliminar);
		file.delete();
		
		
	}

	private static void guardarArrayListJuguetes(ArrayList<Juguete> arjuguetes, String cedula) throws IOException {
		SystemProperties sp2 = new SystemProperties();
		String fichero = sp2.getNombreDirectorioArchivos() + "juguetes-" + cedula + ".txt";
		ArrayList<Object> arobjetos = new ArrayList<Object>();
		for (Juguete jug : arjuguetes) {
			arobjetos.add((Object) jug);
		}

		try {
			ObjectOutputStream ficheroGuardar = new ObjectOutputStream(new FileOutputStream(fichero));
			ficheroGuardar.writeObject(arobjetos);
			ficheroGuardar.flush();
			ficheroGuardar.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("Error: El fichero no existe. ");
		} catch (IOException ioe) {
			System.out.println("Error: Fallo en la escritura en el fichero. ");
		}

	}

	private static ArrayList<Juguete> Leer(String cedula) throws IOException, ClassNotFoundException {
		SystemProperties sp3 = new SystemProperties();
		String fichero = sp3.getNombreDirectorioArchivos() + "juguetes-" + cedula + ".txt";
		ArrayList<Juguete> Juguetes = new ArrayList<>();
		try {
			ObjectInputStream ficheroLeer = new ObjectInputStream(new FileInputStream(fichero));
			ArrayList<Object> aux = (ArrayList<Object>) ficheroLeer.readObject();
			for (Object ob : aux) {
				Juguetes.add((Juguete) ob);
			}
			ficheroLeer.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error: El fichero no existe. ");
		} catch (IOException ioe) {
			System.out.println("Error: Fallo en la lectura en el fichero. ");
		}
		return Juguetes;
	}

}
