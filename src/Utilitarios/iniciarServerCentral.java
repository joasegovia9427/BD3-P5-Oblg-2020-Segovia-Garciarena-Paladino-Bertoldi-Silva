package Utilitarios;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;

import logica.Fachada;
import logica.excepciones.ServidorException;
import persistencia.excepciones.PersistenciaException;

public class iniciarServerCentral {
	
	static boolean isStillRunning = false;
	public static MensajesPersonalizados msg = new MensajesPersonalizados();
	
	public static void main(String[] args) throws PersistenciaException  {
		System.out.println(msg.infoServerInit);
		System.out.println(msg.warningServidorSinHttps);
		try {
			SystemProperties sp = new SystemProperties();
			String ip = sp.getIpServidor();
			String puerto = sp.getPuertoServidor();
			String nombreAPublicar = sp.getNombreAPublicar();
			int port = Integer.parseInt(puerto);

			try {
				LocateRegistry.createRegistry(port);
			} catch (Exception e) {
				// e.printStackTrace();
				LocateRegistry.getRegistry(port);
			}
			// publico el objeto remoto en dicha ip y puerto
			String ruta = "//" + ip + ":" + puerto + "/" + nombreAPublicar;
			Fachada fachadaLogica = Fachada.getInstancia();

			System.out.println("Antes de publicar");
			Naming.rebind(ruta, fachadaLogica);
			Utilitarios.printCompleteDateTime();
			System.out.println(
					"Server Corriendo en la IP::" + ip + " Puerto::" + puerto + " Ruta::"+ruta);
			isStillRunning = true;
			
		
		} catch (RemoteException e) // si ocurre cualquier problema de red
		{
			e.printStackTrace();
		} catch (MalformedURLException e) // si la ruta no esta bien formada
		{
			e.printStackTrace();
		} catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
		{
			e.printStackTrace();
		} catch (IOException e) // si ocurre cualquier otro error de E/S
		{
			try {
				throw new ServidorException(msg.errorIO);
			} catch (ServidorException e1) {
				e.getMessage();
			}
		}
//		Utilitarios.isStillRunning(isStillRunning);
	}


}