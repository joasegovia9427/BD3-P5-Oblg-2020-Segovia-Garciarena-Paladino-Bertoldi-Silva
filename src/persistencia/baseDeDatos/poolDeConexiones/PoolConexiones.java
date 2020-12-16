package persistencia.baseDeDatos.poolDeConexiones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import Utilitarios.Utilitarios;
import logica.excepciones.ServidorException;
import logica.interfaces.IPoolConexiones;
import persistencia.excepciones.PersistenciaException;

public class PoolConexiones implements IPoolConexiones {

	private String driver;
	private String url;
	private String user;
	private String password;

	private int nivelTransaccionalidad;
	private int tamanio;
	private int creadas;
	private int tope;

	private Conexion[] conexiones;
	private SystemProperties sp;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public PoolConexiones() {
		try {
			sp = new SystemProperties();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (Utilitarios.isPoolEnabled()) { // if la propiedad pool_enabled esta en =0 entonces no uso memoria
			try {
				String driver = sp.getMysql_driver();
				Class.forName(driver);
				url = sp.getMysql_url();
				user = sp.getMysql_user();
				password = sp.getMysql_password();
				tamanio = Integer.parseInt(sp.getTamPool());
				nivelTransaccionalidad = Integer.parseInt(sp.getNivelTran());
				System.out.println("nivelTransaccionalidad:"+Integer.toString(nivelTransaccionalidad));
				tope = 0;
				creadas = 0;
				conexiones = new Conexion[tamanio];

			} catch (ClassNotFoundException e) {
				try {
					throw new ServidorException (mensg.errorPoolObtenerClass);
				} catch (ServidorException e1) {
					
					e1.printStackTrace();
				}
				
			}
		}
	}


	public synchronized IConexion obtenerConexion(boolean modifica) {
		// Obtener COnexion
		// -------------------
		// 1)Tengo una conexion en el arreglo con tope disponible para prestar?
		// Si es si la doy,
		// 2)Le doy una del arreglo y bajo el tope
		// 3)) si es no Puedo crear conexion?
		// 4) Si es si La creeo
		// Si es no la mando a dormir
		IConexion con = null;
		if (!Utilitarios.isPoolEnabled()) { // if la propiedad pool_enabled esta en =0 entonces no valido nada el pool y
											// otrogo una IConexion que en el fondo es una Connection
			try {
				con = (IConexion) DriverManager.getConnection(url, user, password);
				if (modifica) {
					((Connection) con).setTransactionIsolation(nivelTransaccionalidad);
					((Connection) con).setAutoCommit(false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("aca");
			while (con == null) { //// espera indefinida
				System.out.println("while en obtener");
				if (tope > 0) {
					con = conexiones[tope - 1];
					tope--;
				} else {
					if (creadas < tamanio) {
						try {
							Connection conAux = DriverManager.getConnection(url, user, password); // var aux que se
							if (modifica) {
								System.out.println("if (modifica) { linea 96");
//								conAux.setTransactionIsolation(nivelTransaccionalidad);
								conAux.setAutoCommit(false);
							}
							con = new Conexion(conAux);
							creadas++;
						} catch (Exception e) {
							try {
								throw new ServidorException (mensg.errorPoolCrearIConexion);
							} catch (ServidorException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						try {
							wait();
						} catch (Exception e) {
							 try {
								throw new ServidorException (mensg.errorPoolWait);
							} catch (ServidorException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}

		}
		return con;
	}

	public synchronized void liberarConexion(IConexion con, boolean ok) {
//    	Liberar conexion
//    	--------------
//    	1)Hace comit o rolback segun corrersponda
//    	2)Devuelve la conexion al arreglo y sube el tope
//    	3)Notify
		Connection conAux = (Connection) con.getConnection();
		try {
			if (ok) {
				conAux.commit();
			} else {
				conAux.rollback();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (Utilitarios.isPoolEnabled()) { // if la propiedad pool_enabled esta en =0 entonces no modifico nada
			conexiones[tope] = (Conexion) con;
			tope++;
			notifyAll();
		}
	}
}
