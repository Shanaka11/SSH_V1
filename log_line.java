//package ssh;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class log_line {

	//This object will receive a String which is a line of a failed entry in auth.log
	//It will contain ip address of the suspect
	//No of attacks by that ip
	//Whether it is blocked or not
	//When was the latest attack
	public String passwd;
	int no_of_attacks = 1;
	String ip = null;
	//String block_time = null;
	boolean block = false;
	
	
	log_line(String in){
		
		this.ip = in;
		
	}
	
	boolean check_ip (String in){
		
		
		if ( this.ip.equals(in)){
			
			increment();
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	boolean check_attack(){
		
		return( this.no_of_attacks == 5 );
		
	}
	
	private void increment(){	
			
		no_of_attacks++;
		
	}
	
	void block_ip(){
		Process p =null;
		String cmd ="sudo iptables -A INPUT -s " + this.ip +" -j DROP";
		try {
		p = Runtime.getRuntime().exec(cmd);
		//p =Runtime.getRuntime().exec("echo " + passwd + " | sudo -k iptables -L\n");
		} catch (IOException e) {
        // Do something here
    }
		System.out.println(this.ip + " Blocked");
		
		//this.block_time = time;
		this.block = true;
		
		//The unblocking of the ip
		
		new release_ip(1000);
		
		
	}
	
	class release_ip{
		Timer timer;
		
		//The unblocking of the ip
		release_ip ( int delay ){
		
			timer = new Timer();
			timer.schedule(new block(), delay);
			
		}
		
		class block extends TimerTask {
			
			public void run() {
				Process p =null;
		String cmd = "sudo iptables -D INPUT -s "+ ip + " -j DROP" ;
		try {
		p = Runtime.getRuntime().exec(cmd);
		//p =Runtime.getRuntime().exec("echo " + passwd + " | sudo iptables -L\n");
		} catch (IOException e) {
        // Do something here
    }
				System.out.println(ip +" Relesed");
				block = false;
				no_of_attacks = 1;
				timer.cancel();
				
			}
			
		}
		
	}
	
}
