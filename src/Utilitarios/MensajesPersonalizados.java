package Utilitarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MensajesPersonalizados {
	
	public String errorCargarMensajes;
	public String errorCargarParametros;
	public String errorJARClassForName;
	public String errorSQLConectarseBase;
	public String errorSQLCrearConexion;
	public String errorSQLCerrarConexion;
	public String errorSQLborrarBase;
	public String errorSQLcrearBase;
	public String errorSQLejecutarUse;
	public String errorSQLcreateTableNinio;
	public String errorSQLcreateTableJuguetes;
	public String errorSQLInsertNinio;
	public String errorSQLInsertJuguete;
	public String errorSQLUpdateNinio;
	public String errorSQLUpdateJuguete;
	public String errorSQLDeleteNinio;
	public String errorSQLDeleteJuguete;
	public String errorSQLclavePrimaria;
	public String errorSQLclaveForanea;
	public String errorPoolConstructor;
	public String errorPoolCrearIConexion;
	public String errorPoolWait;
	public String errorPoolNotify;
	public String errorPoolCommit;
	public String errorPoolRollBack;
	public String errorFachadaConectarse;
	public String errorFachadaGetInstancia;
	public String errorFachadaNuevoNinio;
	public String errorFachadaNuevoJuguete;
	public String errorFachadaYaExisteNinio;
	public String errorFachadaNoExisteNinio;
	public String errorFachadaYaExisteJuguete;
	public String errorFachadaNoExisteJuguete;
	public String errorFachadaListNinios, errorFachadaNoJuguetesNinio;
	public String errorFachadaListJuguetes;
	public String errorFachadaGetDescripcion;
	public String errorFachadaNoJugueteByCINinio;
	public String errorFachadaDeleteJuguetesByCINinio;
	public String warningServidorSinHttps;
	public String infoServerInit;
	public String infoJugueteIngresado, infoNinioIngresado,infoNinioEliminado, errorGraficaCamposVacios, errorGraficaCedulaInvalida;
	public String errorGraficaCedulaConLetras,  errorGraficaSoloNumeros,  errorGraficaCedulaJuguete, errorGraficaNombreApellido;
	public String errorSQLListarJuguetes, errorSQLListarNinios, errorSQLConsultarDescripcion, errorIO,errorPoolObtenerClass ;
	public String infoGraficaListadoEnPantalla, infoGraficaDescEnPantalla, errorSQLConsultarCantidadDeJuguetes,errorSQLKesimo, errorSQLFindNinio;
	public String errorGraficaNoHayNinios, errorGraficaNoHayJuguetes;	
	
	public MensajesPersonalizados(){
		Properties p = new Properties();
		String nomArchivoProperty = "config/mensajes.properties";
		try {
			p.load(new FileInputStream(nomArchivoProperty));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		errorCargarMensajes				 	= p.getProperty("errorCargarMensajes");    
		errorCargarParametros				= p.getProperty("errorCargarParametros");    
		errorJARClassForName			 	= p.getProperty("errorJARClassForName");    
		errorSQLConectarseBase				= p.getProperty("errorSQLConectarseBase");    
		errorSQLCrearConexion				= p.getProperty("errorSQLCrearConexion");    
		errorSQLCerrarConexion 				= p.getProperty("errorSQLCerrarConexion");    
		errorSQLborrarBase				 	= p.getProperty("errorSQLborrarBase");    
		errorSQLcrearBase 					= p.getProperty("errorSQLcrearBase");    
		errorSQLejecutarUse 				= p.getProperty("errorSQLejecutarUse");    
		errorSQLcreateTableNinio 			= p.getProperty("errorSQLcreateTableNinio");    
		errorSQLcreateTableJuguetes 		= p.getProperty("errorSQLcreateTableJuguetes");    
		errorSQLInsertNinio 				= p.getProperty("errorSQLInsertNinio");    
		errorSQLInsertJuguete 				= p.getProperty("errorSQLInsertJuguete");    
		errorSQLUpdateNinio 				= p.getProperty("errorSQLUpdateNinio");    
		errorSQLUpdateJuguete 				= p.getProperty("errorSQLUpdateJuguete");    
		errorSQLDeleteNinio 				= p.getProperty("errorSQLDeleteNinio");    
		errorSQLDeleteJuguete 				= p.getProperty("errorSQLDeleteJuguete");    
		errorSQLclavePrimaria 				= p.getProperty("errorSQLclavePrimaria");    
		errorSQLclaveForanea 				= p.getProperty("errorSQLclaveForanea");    
		errorPoolConstructor				= p.getProperty("errorPoolConstructor");    
		errorPoolCrearIConexion 			= p.getProperty("errorPoolCrearIConexion");    
		errorPoolWait						= p.getProperty("errorPoolWait");    
		errorPoolNotify 					= p.getProperty("errorPoolNotify");    
		errorPoolCommit 					= p.getProperty("errorPoolCommit");    
		errorPoolRollBack 					= p.getProperty("errorPoolRollBack");    
		errorFachadaConectarse 				= p.getProperty("errorFachadaConectarse");    
		errorFachadaGetInstancia 			= p.getProperty("errorFachadaGetInstancia");    
		errorFachadaNuevoNinio 				= p.getProperty("errorFachadaNuevoNinio");    
		errorFachadaNuevoJuguete 			= p.getProperty("errorFachadaNuevoJuguete");    
		errorFachadaYaExisteNinio 			= p.getProperty("errorFachadaYaExisteNinio");    
		errorFachadaNoExisteNinio 			= p.getProperty("errorFachadaNoExisteNinio");    
		errorFachadaYaExisteJuguete 		= p.getProperty("errorFachadaYaExisteJuguete");    
		errorFachadaNoExisteJuguete 		= p.getProperty("errorFachadaNoExisteJuguete");    
		errorFachadaListNinios 				= p.getProperty("errorFachadaListNinios");    
		errorFachadaListJuguetes 			= p.getProperty("errorFachadaListJuguetes");    
		errorFachadaGetDescripcion 			= p.getProperty("errorFachadaGetDescripcion");    
		errorFachadaNoJugueteByCINinio 		= p.getProperty("errorFachadaNoJugueteByCINinio");    
		errorFachadaDeleteJuguetesByCINinio = p.getProperty("errorFachadaDeleteJuguetesByCINinio");
		warningServidorSinHttps 			= p.getProperty("warningServidorSinHttps");    
		infoServerInit 						= p.getProperty("infoServerInit"); 
		infoJugueteIngresado				= p.getProperty("infoJugueteIngresado");
		infoNinioIngresado					= p.getProperty("infoNinioIngresado");
		infoNinioEliminado					= p.getProperty("infoNinioEliminado");
		errorGraficaCamposVacios			= p.getProperty("errorGraficaCamposVacios");
		errorGraficaCedulaInvalida			= p.getProperty("errorGraficaCedulaInvalida");
		errorGraficaCedulaConLetras			= p.getProperty("errorGraficaCedulaConLetras");
		errorGraficaSoloNumeros				= p.getProperty("errorGraficaSoloNumeros");
		errorGraficaCedulaJuguete			= p.getProperty("errorGraficaCedulaJuguete");
		errorGraficaNombreApellido			= p.getProperty("errorGraficaNombreApellido");
		errorFachadaNoJuguetesNinio			= p.getProperty("errorFachadaNoJuguetesNinio");
		errorSQLListarNinios				= p.getProperty("errorSQLListarNinios");
		errorSQLListarJuguetes				= p.getProperty("errorSQLListarJuguetes");
		errorSQLConsultarDescripcion 		= p.getProperty("errorSQLConsultarDescripcion");
		errorIO 							= p.getProperty("errorIO");
		infoGraficaListadoEnPantalla		= p.getProperty("infoGraficaListadoEnPantalla");
		infoGraficaDescEnPantalla			= p.getProperty("infoGraficaDescEnPantalla");
		errorSQLConsultarCantidadDeJuguetes = p.getProperty("errorSQLConsultarCantidadDeJuguetes");
		errorSQLKesimo						=  p.getProperty("errorSQLKesimo");
		errorSQLFindNinio					=p.getProperty("errorSQLFindNinio");
		errorPoolObtenerClass				=p.getProperty("errorPoolObtenerClass");
		errorGraficaNoHayNinios				=p.getProperty("errorGraficaNoHayNinios");
		errorGraficaNoHayJuguetes				=p.getProperty("errorGraficaNoHayJuguetes");
	}

}