package persistencia.baseDeDatos.poolDeConexiones;

import java.sql.Connection;

public class Conexion implements IConexion{

	private Connection con;
	
	public Conexion(Connection con) {
		this.con = con;

		
	}

	public Connection getConnection() {
		return con;
	}
	
	
}