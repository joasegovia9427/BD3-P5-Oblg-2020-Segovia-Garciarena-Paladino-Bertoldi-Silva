package persistencia.factory;

import java.io.IOException;
import java.io.Serializable;

import persistencia.baseDeDatos.daos.IDaoJuguetes;
import persistencia.baseDeDatos.daos.IDaoNinios;
import persistencia.sistemaDeArchivos.daos.daoJuguetesArchivos;
import persistencia.sistemaDeArchivos.daos.daoNiniosArchivos;

public class FabricaArchivo implements IFabricaAbstracta, Serializable {

	private static final long serialVersionUID = 1L;

	public IDaoNinios crearIDaoNinios() {
		try {
			return new daoNiniosArchivos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public IDaoJuguetes crearIDaoJuguetes(int in_ci) {
		System.out.println(
				"++++++++++entro bien a la fabrica de archivo a intentar crear la coleccion de idaojugarchivos");
		return new daoJuguetesArchivos(in_ci);

	}

}
