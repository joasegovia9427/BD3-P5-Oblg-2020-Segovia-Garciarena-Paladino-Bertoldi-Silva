package persistencia.sistemaDeArchivos.daos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utilitarios.SystemProperties;
import grafica.ventanas.AdministradorJugueteria;
import logica.Juguete;
import logica.Ninio;
import logica.valueObjects.voNinio;
import persistencia.baseDeDatos.daos.IDaoNinios;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class daoNiniosArchivos implements IDaoNinios, Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private SystemProperties sp;
	public daoNiniosArchivos() throws IOException {
		sp = new SystemProperties();
	}

	public boolean member(int cedula, IConexion con)  throws PersistenciaException{
			 boolean encontro = false; 
			 String nomArch = sp.getNombreDirectorioArchivos()+"ninio-"+String.valueOf(cedula)+".txt";
			 File fl= new File(nomArch);
			 if(fl.exists())
			 {
				 encontro=true;
			 }
			
		 return encontro;
	}
//no se usa
	public boolean member2(int cedula, IConexion con) throws PersistenciaException  {
		boolean encontro = false; 
		try
			{ 
			 String nomArch = sp.getNombreDirectorioArchivos()+"ninio-"+String.valueOf(cedula)+".txt";
					// Abro el archivo y creo un flujo de comunicacion
					FileInputStream f = new FileInputStream(nomArch);
					ObjectInputStream o = new ObjectInputStream(f);
					// Leo el archivo con el ninio
					Ninio nin = (Ninio) o.readObject();
					int cedulaEncontrada = nin.getCedula();
					if (cedulaEncontrada == cedula)
						encontro = true;
					o.close();
					f.close();
				}catch (IOException | ClassNotFoundException e ){ 
					throw new PersistenciaException("Error al recuperar - Hubo problemas de IO");
				}
		 return encontro;
	}

	public Ninio find(int cedula, IConexion con) throws PersistenciaException {
		Ninio nin=null;
		try
			{ 
			 String nomArch = sp.getNombreDirectorioArchivos()+"ninio-"+String.valueOf(cedula)+".txt";
			 	// Abro el archivo y creo un flujo de comunicacion
				FileInputStream f = new FileInputStream(nomArch);
				ObjectInputStream o = new ObjectInputStream(f);
				// Leo el archivo con el ninio
				nin = (Ninio) o.readObject();
				o.close();
				f.close();
				
				}catch (IOException | ClassNotFoundException e ){ 
					throw new PersistenciaException("Error al recuperar - Hubo problemas de IO");
				}
		return nin;
	}
	
	public void insert(Ninio nin, IConexion con) throws PersistenciaException {
		try
		{  
			int cedula = nin.getCedula();
			String nomArch = sp.getNombreDirectorioArchivos()+"ninio-"+String.valueOf(cedula)+".txt";
			// Abro el archivo
			System.out.println("public void insert(Ninio nin, IConexion con) throws PersistenciaException "+nomArch);
			FileOutputStream f = new FileOutputStream(nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);
			// Escribo el ninio
			o.writeObject (nin);
			o.flush(); //fer: agrego porque se rompe alli
			o.close();
			f.close();
		}
		catch (IOException e){ 
			e.printStackTrace();
			throw new PersistenciaException("error respaldar"+e.getMessage());
		}
	}

	public void delete(int cedula, IConexion con) throws PersistenciaException {
		String nomArch = sp.getNombreDirectorioArchivos()+"ninio-"+String.valueOf(cedula)+".txt";
		File archivo = new File(nomArch);
		archivo.delete();
	}

	public List<voNinio> listarNinios(IConexion con) throws PersistenciaException {
		List<voNinio> listaNinios = new ArrayList<voNinio>();
		String directorio = sp.getNombreDirectorioArchivos();
		File carpeta = new File(directorio);
		
		String[] listado = carpeta.list();
		if (listado == null || listado.length == 0) {
		    return null;
		}
		else {
			Ninio nin=null;
		    for (int i=0; i< listado.length; i++) {
		    	String nomArchivo = listado[i];
		    	boolean esUnArchivoJuguete = false;
		    	esUnArchivoJuguete = NombreDeArchivioEsNinio(nomArchivo);
		    	if (esUnArchivoJuguete) {
			    		daoNiniosArchivos daoAux;
						try {
							daoAux = new daoNiniosArchivos();
						} catch (IOException e) {
							throw new PersistenciaException("error levantar");
						}
			    		
			    		nin = daoAux.findByFileName(nomArchivo);
			    		
						int cedula = nin.getCedula();
						String apellido = nin.getApellido();
						String nombre = nin.getNombre();
						voNinio voNinio =new voNinio(cedula, nombre, apellido);
						listaNinios.add(voNinio);
				}

		    }
		}
		return listaNinios;
	}

	public Ninio findByFileName(String fileName) throws PersistenciaException {
		Ninio nin=null;
		try
			{ 
			 String nomArch =sp.getNombreDirectorioArchivos()+fileName;
			 	// Abro el archivo y creo un flujo de comunicacion
				FileInputStream f = new FileInputStream(nomArch);
				ObjectInputStream o = new ObjectInputStream(f);
				// Leo el archivo con el ninio
				nin = (Ninio) o.readObject();
				o.close();
				f.close();
				}catch (IOException | ClassNotFoundException e ){ 
					throw new PersistenciaException("Error al recuperar - Hubo problemas de IO");
				}
		return nin;
	}
	
	private static boolean NombreDeArchivioEsNinio(String NomArchivo)
	{
		boolean Es=false;
		Pattern patron = Pattern.compile("ninio-");
		Matcher encaja = patron.matcher(NomArchivo);
		Es = encaja.find();

		return Es;
	}
	
}
