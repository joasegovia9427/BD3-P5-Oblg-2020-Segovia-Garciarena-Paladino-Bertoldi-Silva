package Utilitarios;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class SystemProperties implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ipServidor;
	private String puertoServidor;
	private String nombreDeLaBase;
	private String nombreAPublicar;
	private String mysql_driver;
	private String mysql_url;
	private String mysql_dbname;
	private String mysql_user;
	private String mysql_password;
	private String tam_pool;
	private String nivel_tran;
	private String pool_enabled;
	private String pool_className;
	private String daoJuguetes_className;
	private String daoNinios_className;
	private String fabricaabstracta;
	private String nombreDirectorioArchivos;
	

	public SystemProperties() throws IOException {

		Properties p = new Properties();
		String nomArchivoProperty = "config/server.properties";
		p.load(new FileInputStream(nomArchivoProperty));

		ipServidor 			= p.getProperty("ipServidor");
		puertoServidor 		= p.getProperty("puertoServidor");
		nombreDeLaBase 		= p.getProperty("nombreDeLaBase");
		nombreAPublicar 	= p.getProperty("nombreAPublicar");
		
		mysql_driver		= p.getProperty("mysql_driver");
		mysql_url			= p.getProperty("mysql_url");
		mysql_dbname		= p.getProperty("mysql_dbname");
		mysql_user			= p.getProperty("mysql_user");
		mysql_password		= p.getProperty("mysql_password");
		tam_pool            = p.getProperty("tam_pool");
		nivel_tran          = p.getProperty("nivel_tran");
		pool_enabled		= p.getProperty("pool_enabled");
		pool_className			= p.getProperty("pool_className");
		daoJuguetes_className	= p.getProperty("daoJuguetes_className");
		daoNinios_className		= p.getProperty("daoNinios_className");
		fabricaabstracta		=p.getProperty("fabricaabstracta");
		nombreDirectorioArchivos=p.getProperty("nombreDirectorioArchivos");
		
	}
	
	public String getIpServidor() {
		return ipServidor;
	}

	public String getPuertoServidor() {
		return puertoServidor;
	}

	public String nombreDeLaBase() {
		return nombreDeLaBase;
	}

	public String getNombreAPublicar() {
		return nombreAPublicar;
	}

	public String getMysql_driver() {
		return mysql_driver;
	}

	public String getMysql_url() {
		return mysql_url;
	}

	public String getMysql_dbname() {
		return mysql_dbname;
	}

	public String getMysql_user() {
		return mysql_user;
	}

	public String getMysql_password() {
		return mysql_password;
	}
	
	public String getTamPool() {
		return tam_pool; 
	}
	public String getNivelTran() {
		return nivel_tran; 
	}
	
	public String getPool_enabled() {
		return pool_enabled; 
	}
	
	public String getpool_className() {
		return pool_className; 
	}
	
	public String getdaoJuguetes_className() {
		return daoJuguetes_className; 
	}
	
	public String getdaoNinios_className() {
		return daoNinios_className; 
	}
	public String getFabricaAbstracta() {
		return fabricaabstracta; 
	}
	public String getNombreDirectorioArchivos() {
		return nombreDirectorioArchivos; 
	}
	

}
