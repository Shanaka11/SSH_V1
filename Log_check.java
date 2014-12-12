

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is the main class file
public class Log_check {

	
		public static void main(String[] args){
			
			//loading settings
			File file = new File("Config.txt");
			try {
				Scanner x = new Scanner(file);
			
			
			String logFile = x.nextLine();
			x.close();
		
			UnBlocker unBlock = new UnBlocker();
			refresh re = new refresh();
			write_ip wip = new write_ip();
			
			re.start();
			unBlock.start();
			
			wip.fileClean("Blocked_ips.txt");
			wip.fileClean("out_log.txt");
			
			
			try {
				//make the reading of the file continue until specified otherwise
				FileReader flr = new FileReader(logFile);
				BufferedReader rd = new BufferedReader(flr);
				
			
				String line = null;
				
				// This is placed here only temporally we want refresh the table in a given interval 
				Hashtable<String,log_line> f_ips = new Hashtable<String,log_line>();
				
				while (true){
					// to refresh the suspects after a time period
					if (re.tim){
						f_ips = new Hashtable<String,log_line>();
					}
					
					line = rd.readLine();
					if (line == null || line == "\n") {
						
						try {
							
							Thread.sleep(1000);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						
					}else{
						
						if (line.contains("Invalid") || line.contains("PAM") || !line.contains("preauth")){
							//This will extract the ip addressese
							
							Pattern p = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
							Matcher m = p.matcher(line);
						
							if(m.find()) {
							
								//System.out.println(m.group(0));
								String temp = m.group(0);
							
							
							
								
								
								if(f_ips.containsKey(temp)){
								
									f_ips.get(temp).check_ip(temp);
									
									if(f_ips.get(temp).check_attack()){
										//The ip is blocked now
										//This part will add the time the ip is blocked to the list blocked
										Pattern p1 = Pattern.compile("([\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2})");
										Matcher m1 = p1.matcher(line);
										
										if(m1.find()){
											f_ips.get(temp).block_ip(m1.group(0));	
										}
										wip.write_i(temp,"Blocked_ips.txt");
									}
									
								}else{
									
									log_line temp_line = new log_line(temp);
									f_ips.put(temp, temp_line);
								}
							
							}
						}
					}
					
				}
				
				
			} catch (FileNotFoundException e) {
				
				System.out.println("LogFileNotFound");
				e.printStackTrace();
				System.exit(-1);
				
			} catch (IOException r) {
				
				System.out.println("Error");
				r.printStackTrace();
				System.exit(-1);
				
			}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
		
}
