package persistencia.baseDeDatos.poolDeConexiones;

import java.sql.Connection;

public interface IConexion {

	public Connection getConnection(); 
}
