package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import Utilitarios.MensajesPersonalizados;
import logica.Ninio;
import logica.valueObjects.*;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class daoNinios implements IDaoNinios, Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private EntityManagerFactory factory;

	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	public daoNinios (){

	}
	
	public boolean member (int cedula, IConexion con) throws PersistenciaException{
		boolean existe = false;
		try{
			consultas cons = new consultas();
			String query = cons.existeNinio();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt.setInt (1, cedula);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				existe = true;
			rs.close ();
			pstmt.close ();
		}catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindNinio);
		}
		return existe;
	}
	
	public Ninio find (int cedula, IConexion con) throws PersistenciaException{
		Ninio nin = null;
		String nombre= "";
		String apellido="";
		try{
			consultas cons = new consultas ();
		
			String queryNin = cons.obtenerNinio();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryNin);
			pstmt1.setInt (1, cedula);
			ResultSet rs1 = pstmt1.executeQuery ();
			if (rs1.next ()){
				nombre = rs1.getString(1);
				apellido = rs1.getString(2);
			}
			rs1.close ();
			pstmt1.close ();
			nin = new Ninio (cedula, nombre, apellido);
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindNinio);
		}
		return nin;
	}
	
	public void insert (Ninio nin, IConexion con) throws PersistenciaException{
		try{
			consultas cons = new consultas();
			String insert = cons.insertarNinio();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
			pstmt.setInt(1, nin.getCedula());
			pstmt.setString (2, nin.getNombre());
			pstmt.setString (3, nin.getApellido());
			pstmt.executeUpdate ();
			pstmt.close ();
		}
		catch (SQLException e)
		{
		throw new PersistenciaException (mensg.errorSQLInsertNinio);
		}
	}

	 public void delete(int cedula, IConexion con) throws PersistenciaException
	{
		
		try {
			consultas cons = new consultas();
			String deletNinio = cons.borrarNinioJuguetes();
			
			PreparedStatement prstm;
			prstm = ((Conexion) con).getConnection().prepareStatement(deletNinio);
			prstm.setInt(1, cedula);
			prstm.executeUpdate();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLDeleteNinio);
		}
		
	}
	
	public List <voNinio> listarNinios(IConexion con) throws PersistenciaException
	{
		consultas cons = new consultas();
		List<voNinio> listaDeVONinios = new ArrayList<voNinio>();
		String sqlToExecute = cons.listarNinios();
		
		PreparedStatement prstm;
		try {
			
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			
			while (rs.next()) {
			    voNinio nuevoVONinio = new voNinio(rs.getInt(1), rs.getString(2), rs.getString(3));
			    listaDeVONinios.add(nuevoVONinio);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLListarNinios);
		}
		
		
		return listaDeVONinios;
	}
}
