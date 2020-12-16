package persistencia.factory;

import persistencia.baseDeDatos.daos.IDaoJuguetes;
import persistencia.baseDeDatos.daos.IDaoNinios;
import persistencia.baseDeDatos.daos.daoJuguetes;
import persistencia.baseDeDatos.daos.daoNinios;

public class FabricaMySQL implements IFabricaAbstracta{

	public IDaoNinios crearIDaoNinios() {
		return new daoNinios();
	}

	//fernando: verificar que fue necesario crear un constructor sin la cedula para la fabrica
	public IDaoJuguetes crearIDaoJuguetes(int in_ci) {
		return new daoJuguetes(in_ci);
	}

	
	
}
