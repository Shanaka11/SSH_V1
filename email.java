
import java.util.Properties;
//import javax.mail.AuthenticationFailedException;
//import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.*;
import java.util.ArrayList;
public class Email {
 public static  void main(String [] arg){
 String filename="aaa.txt";
 ArrayList <String> text = new ArrayList<String>();
	 try{
	    FileReader FRD=new FileReader(filename);
		BufferedReader bufferRd = new BufferedReader(FRD);
		
		
		String line=null;
		while((line = bufferRd.readLine()) != null){
		text.add(line);
		}
		
	 FRD.close();
	bufferRd.close();
	 }catch(FileNotFoundException x){
	 System.out.println("FileNotFoundException");
	 System.exit(-1);
	 }catch(IOException x){
	  System.out.println("IOException");
	 System.exit(-1);
	 }
	
 
	 String [] message;
	 String to="dhanushkajpt@gmail.com";
	 if(send("tjayawardhana1992@gmail.com","tharindu1992",text,to)){
		 System.out.println("success");
	 }
	 else System.out.println("unsuccess");
 }
public static boolean send(String from,String password,String[] message,String to){
	
	
	String host="smtp.gmail.com";
	Properties props=System.getProperties();
	props.put("mail.smtp.starttls.enable","true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.user", from);
	props.put("mail.smtp.password", password);
	props.put("mail.smtp.port",587);
	props.put("mail.smtp.auth", "true");
	Session session=Session.getDefaultInstance(props, null);
	MimeMessage mime=new MimeMessage(session);
	try{mime.setFrom(new InternetAddress(from));
		InternetAddress [] toAddress=new InternetAddress[to.length()];
		toAddress[0]=new InternetAddress(to);
		mime.addRecipient(RecipientType.TO, toAddress[0]);
		mime.setSubject("the title");
		for(int i=0;i>message.length;i++){
			String text=message[i];
			mime.setText(text);
		}
		Transport tans=session.getTransport("smtp");
		tans.connect(host,from, password);
		tans.sendMessage(mime,mime.getAllRecipients() );
		tans.close();
		return true;
		//toAddress[i] =new InternetAddress(to.le)
	}catch(MessagingException x){
		x.printStackTrace();
		
	}

	return false;
}
}

