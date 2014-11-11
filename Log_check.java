package ssh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is the main class file
public class Log_check {

		public static void main (String[] args){
			
			String logFile = "auth (1).log";
			
			try {
				//make the reading of the file continue until specified otherwise
				FileReader flr = new FileReader(logFile);
				BufferedReader rd = new BufferedReader(flr);
				String line = null;
				
				// This is placed here only temporally we want refresh the table in a given interval 
				Hashtable<String,log_line> f_ips = new Hashtable<String,log_line>();
				
				while ((line = rd.readLine()) != null){
					
					if (line.contains("Invalid") || line.contains("PAM")){
						//This will extract the ip addressese
						
						Pattern p = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
						Matcher m = p.matcher(line);
						
						if(m.find()) {
							
							//System.out.println(m.group(0));
							String temp = m.group(0);
							
							
							
							//Simplify the log_line class when using hashtables things are much easier
								
							if(f_ips.containsKey(temp)){
								
								f_ips.get(temp).check_ip(temp);
									
								if(f_ips.get(temp).check_attack()){
									
									f_ips.get(temp).block_ip();	
								}
									
							}else{
									
								log_line temp_line = new log_line(temp);
								f_ips.put(temp, temp_line);
							}
							
						}
					}
					
				}
				rd.close();
				
				
			} catch (FileNotFoundException e) {
				
				System.out.println("LogFileNotFound");
				e.printStackTrace();
				System.exit(-1);
				
			} catch (IOException r) {
				
				System.out.println("Error");
				r.printStackTrace();
				System.exit(-1);
				
			}
			
		}
		
}
