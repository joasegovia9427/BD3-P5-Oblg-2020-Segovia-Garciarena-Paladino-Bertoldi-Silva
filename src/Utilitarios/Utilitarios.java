package Utilitarios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilitarios {

	static SystemProperties sp;
	static Connection con;
	
	
	public Utilitarios() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isPoolEnabled() {
		boolean isPoolEnabled = false;
		try {
			sp = new SystemProperties();
			String pool_enabled_sp = sp.getPool_enabled();
			int pool_enabled = Integer.parseInt(pool_enabled_sp);
			isPoolEnabled = (pool_enabled == 1) ? true : false;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isPoolEnabled;
	}
	
	public static void printCompleteDateTime() {
		System.out.print(getCompleteDateTime()+"==>");
//		System.out.print(new Date().toString()+"==>");
	}
	
	public static void printTime() {
		System.out.print(new Date().toString()+"==>");
	}
	
	public static String getCompleteDateTime() {
		String YMDHMS = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		YMDHMS = (String) (dtf.format(now));
		
		return YMDHMS;
	}
	
	public static void clearConsole() {
		for (int i=1; i<=1000; i++)
		       System.out.println("\n");
	}
	
	public static Connection createAndGetConnection() throws IOException {
		sp = new SystemProperties();
		String driver = sp.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url 		= sp.getMysql_url();//+sp.getMysql_dbname();
		String user		= sp.getMysql_user();
		String password = sp.getMysql_password();
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void isStillRunning(boolean isStillRunning) {
		if (isStillRunning) {
			int cont = 0;
			int cont2 =0;
			printTime();
			System.out.print("Server is running...\n");
//			try {
//				printTimer();
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			while (isStillRunning) {
				cont+=1;
				if (cont==81 || cont==161) {
					printTime();
					System.out.print("Server is still running...\n");
				}
				if (cont%40==0) {
					System.out.print("\n");
					cont2+=1;
					if (cont2%7==0) {
						int numeroRandom = (int) (Math.random() * cont2) + 1;
//						System.out.print("numeroRandom:"+Integer.toString(numeroRandom)+ " cont2:"+Integer.toString(cont2)+"\n");
						if (numeroRandom >= cont2/2) {
							System.out.print("......si, aun esta corriendo XD... ....\n");
						} else {
							printTime();
							System.out.print("Server is still running...\n");
						}
					}
				} else {
					System.out.print(".");
				}
				try {
					TimeUnit.MILLISECONDS.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}			
			}
		}
	}


	public static void printTimer() throws InterruptedException {
		// TODO Auto-generated method stub
		String text;
		char[] animationChars = new char[] {'|', '/', '-', '\\'};
	    for (int i = 0; i < 1000; i++) {
	    	text = "" + animationChars[i % 4] + '\r';
	        System.out.print(text);

	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
		

//		int i=1, j=1;
//		while (i <25) {
//			switch (j) {
//			case 1: 
//				TimeUnit.MILLISECONDS.sleep(200);
//				text = "|\r";
//				System.out.print(text);
//				break;
//			case 2: 
//				TimeUnit.MILLISECONDS.sleep(200);
//				text = "/\r";
//				System.out.print(text);
//				break;
//			case 3: 
//				TimeUnit.MILLISECONDS.sleep(200);
//				text = "-\r";
//				System.out.print(text);
//				break;
//			case 4: 
//				TimeUnit.MILLISECONDS.sleep(200);
//				text = "\\\r";
//				System.out.print(text);
//				j=0;
//				break;
//			}
//			i+=1;
//			j+=1;
//		}

	}

	public static void getPoolBDClassPkgPath() {
		
	}
	
	private static int rsCountRows(ResultSet rs) {
		int size= 0;  
		if (rs!= null)   
		{  
			try {
				rs.beforeFirst();  
				rs.last();  
				size = rs.getRow();  
				rs.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return size;
	}
		
	
}
