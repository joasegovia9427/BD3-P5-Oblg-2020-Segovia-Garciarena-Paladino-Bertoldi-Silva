package logica.valueObjects;

import java.io.Serializable;

public class voNinio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cedula;
	private String nombre;
	private String  apellido;
	
	public voNinio(int ci,String nom, String ape) {
		cedula=ci;
		nombre=nom;
		apellido=ape;
		
	}
	
	public int getCedula(){
		
		return cedula;
		
		}
	
     public String  getNombre(){
		
		return nombre;
		
		}
     
     public String  getApellido(){
 		
 		return apellido;
 		
 	  }
     
     
     
     
     
	
	

}
