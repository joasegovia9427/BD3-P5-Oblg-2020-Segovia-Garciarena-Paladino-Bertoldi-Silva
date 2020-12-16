package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import Utilitarios.MensajesPersonalizados;
import logica.Juguete;
import logica.Ninio;
import logica.valueObjects.voJuguetes;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.excepciones.PersistenciaException;
import persistencia.baseDeDatos.poolDeConexiones.*;
//fer: agrego Serializable
public class daoJuguetes implements IDaoJuguetes, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private EntityManagerFactory factory;
	private int cedulaNinio;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public daoJuguetes(int in_CedulaNinio) {
		this.cedulaNinio = in_CedulaNinio;							
	}
    //fernando: agrego para poder hacer la fabrica abstracta
	public daoJuguetes() {
		// TODO Auto-generated constructor stub
	}

	public void insBack(Juguete in_JugueteNuevo, IConexion con) throws PersistenciaException {
		try {
			consultas cons = new consultas();
			String query = cons.insertarJuguete();
			PreparedStatement pstmt =((Conexion) con).getConnection().prepareStatement(query);
			pstmt.setInt(1, in_JugueteNuevo.getNumero());
			pstmt.setString(2, in_JugueteNuevo.getDescripcion());
			pstmt.setInt(3, in_JugueteNuevo.getCedulaNinio());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLInsertJuguete);
		}
	}

	public int largo( IConexion con) throws PersistenciaException {
		int out_LargoDeJuguetes = 0;
		try {
			consultas cons = new consultas();

			String query = cons.cantidadJuguetesNinio();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt1.setInt(1,this.cedulaNinio);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				out_LargoDeJuguetes =rs1.getInt("cantidad");
			}
			rs1.close();
			pstmt1.close();

		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLConsultarCantidadDeJuguetes);
		}
		return out_LargoDeJuguetes;
	}

	
	public Juguete kesimo(int indice,  IConexion con) throws PersistenciaException {
		consultas cons = new consultas();
		List<Juguete> out_ListJuguetes = new ArrayList<Juguete>();

		try {

			String query = cons.listarJuguetes();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt1.setInt(1, this.cedulaNinio);
			ResultSet rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				String descripcion = rs1.getString("descripcion");
				int cedulaNinio = rs1.getInt("cedulaNinio");
				int numero = rs1.getInt("numero");
				Juguete j = new Juguete(descripcion, numero, cedulaNinio);
				out_ListJuguetes.add(j);
			}
			

		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLKesimo);

		}
		
		return out_ListJuguetes.get(indice);

	}


	public List<voJuguetes> listarJuguetes(IConexion con) throws PersistenciaException {
		List<voJuguetes> out_ListJuguetes = new ArrayList<voJuguetes>();
		
		try {
			consultas cons = new consultas();
			String query = cons.listarJuguetes();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt.setInt (1, this.cedulaNinio);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt(1);
				String desc = rs.getString(2);
				int ced = rs.getInt(3);
				voJuguetes jug = new voJuguetes(num, desc, ced);
				out_ListJuguetes.add(jug);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLListarJuguetes);
		}
		return out_ListJuguetes;

	}
	
	public void borrarJuguetes( IConexion con) throws PersistenciaException {
		try {
			consultas cons = new consultas();
			String query = cons.borrarJugetesNinio();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt.setInt(1, this.cedulaNinio);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLDeleteJuguete);
		}
	}
}