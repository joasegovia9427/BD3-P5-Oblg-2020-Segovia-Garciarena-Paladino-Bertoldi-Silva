package Utilitarios;

import persistencia.baseDeDatos.daos.daoJuguetes;
import persistencia.baseDeDatos.daos.daoNinios;
import persistencia.baseDeDatos.poolDeConexiones.PoolConexiones;
import persistencia.factory.FabricaMySQL;

import persistencia.sistemaDeArchivos.daos.daoJuguetesArchivos;
//import persistencia.sistemaDeArchivos.daos.daoNiniosArchivos;
//import persistencia.sistemaDeArchivos.poolDeConexiones.PoolConexionesArchivos;
//import persistencia.sistemaDeArchivos.poolDeConexiones.PoolConexionesArchivos;
import persistencia.factory.FabricaArchivo;

public class GetClassPgkPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PoolConexiones poolBD = new PoolConexiones();
		String path = poolBD.getClass().getCanonicalName();
		System.out.println("             PoolBD-->"+path+"<--");
		
//		PoolConexionesArchivos poola = new PoolConexionesArchivos();
//		path = poola.getClass().getCanonicalName();
//		System.out.println("        PoolArchivo-->"+path+"<--");
		
		daoNinios daoNiniosBD = new daoNinios();
		path = daoNiniosBD.getClass().getCanonicalName();
		System.out.println("        daoNiniosBD-->"+path+"<--");
		
//		daoNiniosArchivos daoNiniosArchivos = new daoNiniosArchivos();
//		path = daoNiniosArchivos.getClass().getCanonicalName();
//		System.out.println("  daoNiniosArchivos-->"+path+"<--");
		
		daoJuguetes daoJuguetes = new daoJuguetes(0);
		path = daoJuguetes.getClass().getCanonicalName();
		System.out.println("      daoJuguetesBD-->"+path+"<--");
		
		daoJuguetesArchivos daoJuguetesArchivos = new daoJuguetesArchivos(0);
		path = daoJuguetesArchivos.getClass().getCanonicalName();
		System.out.println("daoJuguetesArchivos-->"+path+"<--");
			
		FabricaMySQL fabricaMySql = new FabricaMySQL();
		path = fabricaMySql.getClass().getCanonicalName();
		System.out.println("       fabricaMySql-->"+path+"<--");
		
		FabricaArchivo fabricaArchivo = new FabricaArchivo();
		path = fabricaArchivo.getClass().getCanonicalName();
		System.out.println("     fabricaArchivo-->"+path+"<--");
		

	}

}
