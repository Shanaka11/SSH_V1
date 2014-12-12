package kk;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.awt.Desktop;
public class map {
	public static String url;
	public static void main (String [] args) throws IOException{
		Double local_id= Ip(args[0]);
	FileInputStream inputStream = null;
	Scanner sc = null;
	try {
		try {
	    inputStream = new FileInputStream("src/kk/GeoLiteCity-Blocks.csv");
		} catch (FileNotFoundException ex) {
			   System.out.println("File not found !");
			}
	    sc = new Scanner(inputStream, "UTF-8");
	    while (sc.hasNextLine()) {
	        String line = sc.nextLine();
	       String [] k = line.split("\"");
	       if (Double.parseDouble(k[1]) <= local_id && Double.parseDouble(k[3]) >= local_id) {System.out.println(k[5]);
	       location(k[5]);}
	       			
	    }//System.out.println("not found");
	    // note that Scanner suppresses exceptions
	    if (sc.ioException() != null) {
	        throw sc.ioException();
	    }
	} finally {
	    if (inputStream != null) {
	        inputStream.close();
	    }
	    if (sc != null) {
	        sc.close();
	    }
	}
  }
	
public static void location(String id) throws IOException {
		
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			try {
		    inputStream = new FileInputStream("src/kk/GeoLiteCity-Location.csv");
			} catch (FileNotFoundException ex) {
				   System.out.println("File not found !");
				}
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		       String [] k = line.split(",");
		       if (id.equals(k[0])){
		      //  System.out.println(k[5] + "," + k[6]);
		        
		       
		    //  System.out.println(u);
		       url = "https://www.google.lk/maps/place//@"+k[5] + ","+ k[6]+",3z/data=!4m2!3m1!1s0x0:0x0";
		        
		        openWebpage(url);
		       }
		    }//System.out.println("not found");
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		
	}


public static void openWebpage(String urlString) {
    try {
        Desktop.getDesktop().browse(new URL(urlString).toURI());
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static Double Ip(String address){

String [] o = address.split("\\.");
 

 
 Double integer_ip =   ( 16777216 * Double.parseDouble(o[0]) )
             + (    65536 * Double.parseDouble(o[1]) )
             + (      256 * Double.parseDouble(o[2]) )
             +             Double.parseDouble(o[3]);
 System.out.println(integer_ip);

return integer_ip;
}

}
