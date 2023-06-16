package back;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro {
	@SuppressWarnings("deprecation")
	public static void  enviarEmailParaCliente(Evento e,String a) {
	SimpleEmail email = new SimpleEmail(); 

    try {  
    //email.setDebug(true);  //modo debug
    email.setHostName("smtp.gmail.com");  
    email.setAuthentication("naoeosedex@gmail.com","wunqwucukceahrpt");  
    email.setSSL(true);  
    email.addTo(e.getUsuario().getEmail()); //destinatario_email 
    email.setFrom(e.getUsuario().getEmail()); //destinatario
    email.setSubject("contrato");  
    email.setMsg(e.toString()+"\n" + a);  
    email.send();  

    } catch (EmailException ex) {  

    System.out.println("erro");  
    	}
	}
}
