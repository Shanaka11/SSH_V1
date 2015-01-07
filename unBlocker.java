import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class UnBlocker extends Thread {
	
	write_ip wt = new write_ip();
	String tm;
	
	
	
	
	public void run(){
		
		Scanner x = null;
		String tm = null;
		//loading settings
		File file = new File("Config.txt");
		try {
			
			x = new Scanner(file);
			tm = x.nextLine();
			tm = x.nextLine();
			x.close();
			
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
		Time_t temp,unblockTime = null,interval = new Time_t(tm);
		// temp values
		Time_t incr = new Time_t("0:0:1"),hold = null;
		int i = 0;
		
		
		while(true){
			
			if(log_line.blocked.isEmpty()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				temp = log_line.blocked.get(0).time_blocked;
				
//******** ONLY FOR TESTING ********* ONLY FOR TESTING ********* ONLY FOR TESTING ********* ONLY FOR TESTING *********// 
				
				/*if(hold != null){
					System.out.println(hold.hr + ":" + hold.mn + ":"+ hold.se);
					System.out.println("unh" + unblockTime.hr + ":" + unblockTime.mn + ":"+ unblockTime.se);
				}*/
				
				//for testing (Making sure this happens only once)
				
				if (i == 0){
					hold = log_line.blocked.get(0).time_blocked;
					unblockTime = temp.add(interval);
					i = 1;
				}
			
				//This will be used for testing
				if(unblockTime.equals(hold)){
				
					unblock(log_line.blocked.get(0).ip);
					log_line.blocked.remove(0);
					i = 0;
				}else{
				
					hold.addto(incr);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				}
//******** ONLY FOR TESTING ********* ONLY FOR TESTING ********* ONLY FOR TESTING ********* ONLY FOR TESTING *********// 
				//this will compare unblock time to system time if its equal to it then ip will be unblocked
				
//******** REAL IMPLEMNETATION ******** REAL IMPLEMNETATION ******** REAL IMPLEMNETATION ******** REAL IMPLEMNETATION//				
				/*Calendar cal = Calendar.getInstance();
		    	cal.getTime();
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    	Time_t nowTime = new Time_t(sdf.format(cal.getTime()));
		    	
		    	if (i == 0){
					hold = log_line.blocked.get(0).time_blocked;
					unblockTime = temp.add(interval);
					i = 1;
		    	}
		    	
				if(unblockTime.equals(nowTime)){
				
					unblock(log_line.blocked.get(0).ip);
					log_line.blocked.remove(0);
					i = 0;
				}else{
				
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				}*/
			}
			
		}
		
	}
	
	public void unblock(String ip){
		// Prints the command
		tm = "iptables -D -s " + ip + " -j DROP";
		wt.write_i(tm, "out_log.txt");
		
		// Executes it
		/*Process p =null;
		String cmd = "sudo iptables -D INPUT -s "+ ip + " -j DROP" ;
		
		try {
			p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
	}
}
