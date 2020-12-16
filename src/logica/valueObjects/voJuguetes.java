package logica.valueObjects;

import java.io.Serializable;

public class voJuguetes implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private String descripcion;
	private int cedulaNinio;
	
	public voJuguetes(int num,String des, int ci){
		numero=num;
		descripcion=des;
		cedulaNinio=ci;
	}
	
	public int getNumero(){
		
		return numero;
		
		}
	
     public String getDescripcion(){
		
		return descripcion;
		
		}
     
     public int getCedulaNinio(){
 		
       return cedulaNinio;
 		
 	  }
     
     
     
     
     
	

}
