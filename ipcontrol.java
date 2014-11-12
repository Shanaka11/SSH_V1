import java.io.*;  
    public class ipcontrol {  
     public static void main(String args[]) {  
	Process p =null;
	//String[] a =args;
       // String [] b =a.split(",");
       String cmd ="sudo iptables -A INPUT -s " + args[1] +" -j DROP";
	String cmd1 ="sudo iptables -D INPUT -s " + args[1] +" -j DROP";
	
       try {  
	if(args[0].equals("block")){
	 p = Runtime.getRuntime().exec(cmd);}  
	else if(args[0].equals("unblock")){
	 p = Runtime.getRuntime().exec(cmd1);}

	BufferedReader in = new BufferedReader(  
                                new InputStreamReader(p.getInputStream()));  
	String line = null;  
	while ((line = in.readLine()) != null) {  
                System.out.println(line);  
	}  
	} 
	catch (IOException e) {  
		e.printStackTrace();  
	} 
    } 
 }
