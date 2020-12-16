package persistencia.baseDeDatos.consultas;

public class consultas {
	//Agregadas por practico 4
	public String existeNinio(){
		String query="select * from JUGUETERIA.ninio where cedula=(?)";
		return query;
		
	}
	
	public String existeJuguete(){
		String query="select * from JUGUETERIA.juguetes where numero=(?)";
		return query;
		
	}
	
	public String insertarNinio() {
		String query="insert into JUGUETERIA.ninio values(?,?,?)";
		return query;
	}
	
	public String darNumeroUltimoJugNin() {
		String query="select J.numero from JUGUETERIA.juguetes J, JUGUETERIA.ninio N"
				       + " where N.cedula=(?) and N.cedula=J.cedulaninio group by J.numero ORDER BY (J.numero)DESC LIMIT 1";
		return query;
		
	}
	
	public String insertarJuguete() {
		String query="insert into JUGUETERIA.juguetes values(?,?,?)";
		return query;
	}
	
	public String listarNinios() {
		String query="select * from JUGUETERIA.ninio ORDER BY cedula";
		return query;
	}
	
	public String listarJuguetes() {
		String query="select * From JUGUETERIA.juguetes where cedulaninio=(?) ORDER BY numero";
		return query;
		}
	
	public String obtenerNinio() {
		String query="select N.nombre, N.apellido from JUGUETERIA.ninio N"
			       + " where N.cedula=(?)";
		return query;
	}
	
	public String obtenerJuguete() {
		String query="select * from JUGUETERIA.juguetes J"
			       + " where J.numero=(?) and J.cedulaninio=(?)";
		return query;
	}
	
	public String existeNumeroJugueteNinio() {
		String query="select * From JUGUETERIA.juguetes where cedulaninio=(?) and numero=(?)";
		return query;
		
	}
	
	public String darDescripcion() {
		String query="select descripcion from JUGUETERIA.juguetes where cedulaninio=(?) and numero=(?) ";
		return query;
	}

	public String borrarNinioJuguetes() {
		String query="DELETE FROM JUGUETERIA.ninio where cedula=(?) ";
		return query;
				
	}
	//Agregadas por practico 5
	
	public String borrarJugetesNinio() {
		String query="DELETE FROM JUGUETERIA.juguetes where cedulaninio=(?)";
		return query;
	}
	
	public String cantidadJuguetesNinio() {
		String query="SELECT count(*) as cantidad FROM JUGUETERIA.juguetes where cedulaninio=(?)";
		return query;
	}
	
}
