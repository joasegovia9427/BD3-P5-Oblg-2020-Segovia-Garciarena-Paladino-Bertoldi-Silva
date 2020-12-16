package persistencia.baseDeDatos.daos;

import java.util.*;
import com.mysql.jdbc.Driver;

import logica.valueObjects.voJuguetes;
import logica.valueObjects.voNinio;
import persistencia.baseDeDatos.consultas.consultas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class accesoBD {
	
	consultas consultasSql = new consultas();
	
	public boolean existeNinio(Connection con, int in_Cedula) throws SQLException {
		boolean isExisteNinio = false;
		String sqlToExecute = consultasSql.existeNinio();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Cedula);
		ResultSet rs = prstm.executeQuery();
		
		isExisteNinio = (rs.next()) ? true : false;
		rs.close();
		prstm.close();		
		return isExisteNinio;
	}
	
	public int insertarNinio(Connection con, int in_Cedula, String in_Nombre, String in_Apellido) throws SQLException {
		String sqlToExecute = consultasSql.insertarNinio();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Cedula);
		prstm.setString(2, in_Nombre);
		prstm.setString(3, in_Apellido);
		int rowAfectedCount = prstm.executeUpdate();
		
		prstm.close();
		return rowAfectedCount;
	}
	
	public int darNumeroUltimoJugNin(Connection con, int in_Cedula) throws SQLException {
		int numeroUltimoJugNin = 0;
		String sqlToExecute = consultasSql.darNumeroUltimoJugNin();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Cedula);
		ResultSet rs = prstm.executeQuery();
		
		if (rs.next()) {
			numeroUltimoJugNin = rs.getInt(1);		
		}
		
		rs.close();
		prstm.close();
		return numeroUltimoJugNin;
	}

	public int insertarJuguete(Connection con, int in_Numero, String in_Descripcion, int in_CedulaNinio) throws SQLException {
		String sqlToExecute = consultasSql.insertarJuguete();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Numero);
		prstm.setString(2, in_Descripcion);
		prstm.setInt(3, in_CedulaNinio);
		int rowAfectedCount = prstm.executeUpdate();
		prstm.close();
		return rowAfectedCount;
	}
	
	public List<voNinio> listarNinios(Connection con) throws SQLException {
		List<voNinio> listaDeVONinios = new ArrayList<voNinio>();
		String sqlToExecute = consultasSql.listarNinios();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		ResultSet rs = prstm.executeQuery();
		
		while (rs.next()) {
		    voNinio nuevoVONinio = new voNinio(rs.getInt(1), rs.getString(2), rs.getString(3));
		    listaDeVONinios.add(nuevoVONinio);
		}
		rs.close();
		prstm.close();
		
		return listaDeVONinios;
	}
	
	public List<voJuguetes> listarJuguetes(Connection con, int in_Cedula) throws SQLException {
		List<voJuguetes> listaDeVOJuguetes = new ArrayList<voJuguetes>();
		String sqlToExecute = consultasSql.listarJuguetes();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt (1, in_Cedula);
		ResultSet rs = prstm.executeQuery();
		
		while (rs.next()) {
			voJuguetes nuevoVOJuguetes = new voJuguetes(rs.getInt(1), rs.getString(2), rs.getInt(3));
			listaDeVOJuguetes.add(nuevoVOJuguetes);
		}
		rs.close();
		prstm.close();
		return listaDeVOJuguetes;
	}
	
	public boolean existeNumeroJugueteNinio(Connection con, int in_Cedula , int in_Numero) throws SQLException {
		boolean isExisteNumeroJugueteByNinio = false;
		String sqlToExecute = consultasSql.existeNumeroJugueteNinio();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Cedula);
		prstm.setInt(2, in_Numero);
		ResultSet rs = prstm.executeQuery();
		
		isExisteNumeroJugueteByNinio = (rs.next()) ? true : false;
		rs.close();
		prstm.close();
		return isExisteNumeroJugueteByNinio;
	}

	public String darDescripcion(Connection con, int in_Cedula , int in_Numero) throws SQLException {
		String out_descripcion = "";
		String sqlToExecute = consultasSql.darDescripcion();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Cedula);
		prstm.setInt(2, in_Numero);
		ResultSet rs = prstm.executeQuery();
		
		if (rs.next()) {
			out_descripcion = rs.getString(1);		
		}
		rs.close();
		prstm.close();		
		return out_descripcion;
	}
	
	public int BorrarNinioJuguetes(Connection con, int in_Cedula) throws SQLException {
		String sqlToExecute = consultasSql.borrarNinioJuguetes();
		
		PreparedStatement prstm = con.prepareStatement(sqlToExecute);
		prstm.setInt(1, in_Cedula);
		int rowAfectedCount = prstm.executeUpdate();
		prstm.close();
		return rowAfectedCount;
	}
}

/*
String existeNinio() {String query="select * from JUGETERIA.ninio where cedula=(?)";
String insertarNinio() {String query="insert into JUGETERIA.ninio values(?,?,?)";
String darNumeroUltimoJugNin() {String query="select J.numero from JUGETERIA.juguetes J, JUGETERIA.ninio N"
			       + " where N.cedula=(?) and N.cedula=J.cedulaninio group by J.numero ORDER BY (J.numero)DESC LIMIT 1";
String insertarJuguete() {String query="insert into JUGETERIA.juguetes values(?,?,?)";
String listarNinio() {String query="select * from JUGETERIA.ninio ORDER BY ninio";
String listarJuguetes() {String query="select * From JUGETERIA.juguetes where cedulaninio=(?) ORDER BY numero";
String existeNumeroJugueteNinio() {String query="select * From JUGETERIA.juguetes where cedulaninio=(?) and numero=(?)";
String darDescripcion() {String query="select descripcion JUGETERIA.juguetes where cedulaninio=(?) and numero=(?) ";
String BorrarNinioJuguetes() {String query="DELETE FROM JUGETERIA.ninio where cedula=(?) ";
*/