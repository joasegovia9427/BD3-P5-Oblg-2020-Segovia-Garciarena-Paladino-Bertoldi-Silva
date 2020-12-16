package persistencia.factory;

import java.io.IOException;

import persistencia.baseDeDatos.daos.IDaoJuguetes;
import persistencia.baseDeDatos.daos.IDaoNinios;

public interface IFabricaAbstracta {

	public IDaoNinios crearIDaoNinios();
	public IDaoJuguetes crearIDaoJuguetes(int in_ci);
	
}

/*
 
 Creaci�n de la interface IFabricaAbstracta e implementaci�n de las clases FabricaMySQL
y FabricaArchivo correspondientes al patr�n Abstract Factory presentado en el te�rico. La
IFabricaAbstracta ser� utilizada del modo visto en clase y las clases FabricaMySQL y
FabricaArchivo instanciar�n las clases DAO que acceden al mecanismo concreto de
persistencia correspondiente a cada una, tambi�n del modo visto en el curso. La f�brica se
utilizar� desde la Fachada para instanciar el DAO de Ni�os que corresponda y desde el
m�todo constructor de la clase Ni�o para instanciar el DAO de Juguetes que corresponda.
 * */

