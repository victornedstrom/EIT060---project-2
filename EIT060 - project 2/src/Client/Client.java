package Client;

import javax.net.ssl.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;

public class Client {
	
	
	public Client(){
		
       connectServer("127.0.0.1",9996);
		
	}
	
	public void connectServer(String host, int port){// Porten och hosten som vi ska connecta till. 
		SSLSocketFactory factory = null;	
	try{
			
		SSLContext ctx = null;
        KeyManagerFactory kmf;
        KeyStore ks;
        
        char[] passphrase = "password".toCharArray();
        
        ctx = SSLContext.getInstance("TLS");
        kmf = KeyManagerFactory.getInstance("SunX509");
        ks = KeyStore.getInstance("JKS");
        
        
       
        File file = new File("/h/d3/r/dic11glu/Windows/workspace/EIT060-Client/src/keystore.jks");
        ks.load(new FileInputStream(file), passphrase);  // Här ska vi hämta vår klients, doktor/nurse.  deras keystore, och kötta med deras lösenord. 
         kmf.init(ks, passphrase);
         
         
         
         
        ctx.init(kmf.getKeyManagers(), null, null);
        factory = ctx.getSocketFactory();
        
       
		SSLSocket socket = (SSLSocket)factory.createSocket(host, port);  
		socket.setUseClientMode(true);
		if(socket.isConnected()){
			System.out.println("Socket is connected");
			
		}
		
		System.out.println("Da shit havent given error in da client yao");
	
		socket.startHandshake();
		
		System.out.println("SOP efter handshake");
		
	}catch(Exception e){		
		e.printStackTrace();		
	} 
	

	}
	


}