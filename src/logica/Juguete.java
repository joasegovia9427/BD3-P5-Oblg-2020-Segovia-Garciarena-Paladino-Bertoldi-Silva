package logica;

import java.io.Serializable;

public class Juguete implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;
	 private int numero;
	 private int cedulaNinio;
	 
	 
	 public Juguete(String desc, int num, int ced){
		 descripcion = desc;
		 numero = num;
		 cedulaNinio = ced;	 
	 }
	 
	 public String getDescripcion() {
		 return this.descripcion;
	 }
	 
	 public int getNumero() {
		 return this.numero;
	 }

	 public int getCedulaNinio() {
		 return this.cedulaNinio;
	 }

	public void setDescription (String desc){ 
		 this.descripcion = desc;
	  }
	  
	  public void setNumero(int num){ 
		 this.numero = num;
	  }
	  
	  public void setCedulaNinio(int ced){ 
		 this.cedulaNinio = ced;
	  }
	 

}
