package Utilitarios;

import java.util.*;
import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBase {
	
	static SystemProperties sp, sp2;
	static Connection con;
	
	public static void main(String[] args) {
		System.out.println("===============holaMundo=========Practico4========================");
		try {
			sp = new SystemProperties();
			sql_conectarBase();
			System.out.println("...abriendo conexion con el dbms:"+sp.getMysql_url());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sql_crearBaseYCargarla();
		
		sql_cerrarBase();
		System.out.println("========================chauMundo=============================================");		
	}

	private static void sql_crearBaseYCargarla() {
		String sql_tempVarName, sql_temp;
		
		List<String> vars = new LinkedList<String>();
		vars.add("DROP DATABASE IF EXISTS JUGETERIA;");
		vars.add("DROP DATABASE IF EXISTS JUGUETERIA;"); //DROP SCHEMA IF EXISTS JUGETERIA;
		vars.add("CREATE DATABASE JUGUETERIA;");
		vars.add("USE JUGUETERIA;");
		vars.add("CREATE TABLE ninio (cedula int not null PRIMARY KEY," +
											 "nombre varchar(45)," +
											 "apellido varchar(45) );");
		vars.add("CREATE TABLE juguetes (numero int not null," +
											 	"descripcion varchar(45)," +
											 	"cedulaninio int not null, CONSTRAINT FKtoNinio FOREIGN KEY (cedulaninio) REFERENCES ninio(cedula) "+
											 	" ON DELETE CASCADE );");
		
		vars.add("Insert into ninio values (1234567,'Kevin','McCallister');");
		vars.add("Insert into ninio values (2345678,'Matilda','Wormwood');");
		vars.add("Insert into ninio values (3456789,'Harry','Potter');");
		vars.add("Insert into ninio values (4567890,'Merlina','Adams');");
		
		vars.add("insert into juguetes values(2,'jueguete 1 para el niño 1234567',1234567);");
		vars.add("insert into juguetes values(1,'jueguete 1 para el niño 2345678',2345678);");
		vars.add("insert into juguetes values(2,'jueguete 2 para el niño 2345678',2345678);");
		vars.add("insert into juguetes values(3,'jueguete 3 para el niño 2345678',2345678);");
		
		
		Statement stmt = null;
		int i=1;
		for(String sql : vars) {            
			System.out.println("- - - - - - - - - - - - - - - - - - - -"); 
			sql_tempVarName = "sql_"+i;
			sql_temp		= sql;
			System.out.println("SQL::"+sql_tempVarName+"="+sql_temp);
			i++;
			try {
				stmt = con.createStatement();
				int cant = stmt.executeUpdate(sql_temp);
				stmt.close();
				System.out.println("Resultado de ==>" + sql_temp + "<==");
				System.out.println(cant + " filas afectadas");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
		}
	}

	public static void  sql_conectarBase()  {
		String driver = sp.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = sp.getMysql_url();//+sp.getMysql_dbname();
		try {
			con = DriverManager.getConnection(url, sp.getMysql_user(), sp.getMysql_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void  sql_cerrarBase(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws IOException {
		sp2 = new SystemProperties();
		String driver = sp2.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = sp2.getMysql_url();//+sp.getMysql_dbname();
		try {
			con = DriverManager.getConnection(url, sp2.getMysql_user(), sp2.getMysql_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
