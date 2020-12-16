package persistencia.sistemaDeArchivos;
//
//import java.io.*;
//import java.util.Vector;
//import Logic.Mensajes;
//import Logic.SystemProperties;
//import Exceptions.PersistenciaException;

public class PersistenciaRecuperarRespaldar {

//	private SystemProperties sp;
//	@SuppressWarnings("unused")
//	private Mensajes msg;
//
//	public PersistenciaRecuperarRespaldar() throws FileNotFoundException, IOException {
//		sp = new SystemProperties();
//		msg = new Mensajes();
//
//	}
//
//	public boolean verificarExistenciaYContenidoArchivo() throws IOException {
//		boolean existeYcontiene = false;
//		//// VALIDO QUE EXISTA
//		existeYcontiene = new File(sp.getNombreArchivo()).isFile();
//		if (!existeYcontiene) {
//			// System.out.println("No se encontro el archivo, no se cargaran datos");
//		} else {
//			//// y QUE TENGA DATOS
//			BufferedReader br = new BufferedReader(new FileReader(sp.getNombreArchivo()));
//			if (br.readLine() == null) {
//				existeYcontiene = false;
//				// System.out.println("Se encontro un archivo vacio, no se cargaran datos");
//			} else {
//				existeYcontiene = true;
//				// System.out.println("Se encontro un archivo con datos, si se cargaran datos");
//			}
//			br.close();
//		}
//		// System.out.println(":::::::::::::::::::::");
//		return existeYcontiene;
//	}
//
//	public void respaldar(Vector<PersistenceStructuresManagement> arre) throws PersistenciaException {
//		try {
//
//			// Abro el archivo y creo un flujo de comunicaci�n hacia �l ::: 25253354
//			FileOutputStream f = new FileOutputStream(sp.getNombreArchivo());
//			ObjectOutputStream o = new ObjectOutputStream(f);
//			// Escribo el arreglo de veh�culos en el archivo a trav�s del flujo
//
//			o.writeObject(arre);
//			o.close();
//			f.close();
//		} catch (IOException e) {
//			throw new PersistenciaException(Mensajes.M_PERSISTENCIA_RESPALDAR + " - Error detalle : " + e.getMessage());
//		}
//	}
//
//	public Vector<PersistenceStructuresManagement> recuperar() throws PersistenciaException, ClassNotFoundException {
//
//		try {
//			// Abro el archivo y creo un flujo de comunicaci�n hacia �l
//			FileInputStream f = new FileInputStream(sp.getNombreArchivo());
//			ObjectInputStream o = new ObjectInputStream(f);
//			// Leo el arreglo de veh�culos desde el archivo a trav�s del flujo
//			@SuppressWarnings("unchecked")
//			Vector<PersistenceStructuresManagement> arre = (Vector<PersistenceStructuresManagement>) o.readObject();
//			o.close();
//			f.close();
//			return arre;
//
//		} catch (IOException e) {
//			throw new PersistenciaException(Mensajes.M_PERSISTENCIA_RECUPERAR);
//		}
//	}
}
