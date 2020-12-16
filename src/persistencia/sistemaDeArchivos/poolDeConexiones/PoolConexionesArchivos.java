package persistencia.sistemaDeArchivos.poolDeConexiones;

import logica.interfaces.IPoolConexiones;
import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;

public class PoolConexionesArchivos implements IPoolConexiones {

	//private ConexionArchivos conexionArchivosUtilitarios;
	private SystemProperties sp;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	private static int cantLectores; // especifica numero de lectores leyendo
	private static boolean escribiendo; // especifica si alguien esta escribiendo

	public PoolConexionesArchivos() {
		cantLectores = 0;
		escribiendo = false;
	}

	public synchronized IConexion obtenerConexion(boolean modifica) {
		////EQUIVALE AL COMIENZO LECTURA/ESCRITURA dependiendo si modifica o no
		////Si va a solo leer, el modifica entra en false
		System.out.println("entro al OBTENER CONEXION ARCHIVOS LINEA 24");
		IConexion con = null;
		try {
			if (modifica) {
				while ((cantLectores > 0) || (escribiendo == true)) {
					wait();
				}
				escribiendo = true;
			}
			else {
				while (escribiendo == true) {
					wait();// wait(10000);
				}
				cantLectores++;
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;	
	}
	

	public synchronized void liberarConexion(IConexion con, boolean ok) {
		////EQUIVALE AL TERMINO LECTURA/ESCRITURA 
		////dependiendo de la var global escribiendo
		////la var ok no se usa internamente
		
//		try {
			if (escribiendo) {
				////Si se estaba escribiendo... 
				escribiendo = false;
				notifyAll();
			} else {
				////Si no se estaba escribiendo y solo era lectura
				cantLectores--;
				notifyAll();
			}
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
	}
	
}





