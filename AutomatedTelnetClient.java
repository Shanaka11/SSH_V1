package ssh;

import java.io.IOException;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutomatedTelnetClient {
    private final TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String prompt = "#";
    public String Submask;

    public String AutomatedTelnet(String server, String user, String password,String ip) {


        try {
// Connect to the specified server
            telnet.connect(server, 23);

// Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            
          

// Log the user on
            readUntil("RS_AS3303>");
            write("show ip bgp  " + ip);

            readUntil("login: ");
            write(user);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
         //this.Submask =line.substring(28);
        return Submask;
        
    }


    public String readUntil(String pattern) {
        try {
            int k=0;
            String line="";
       
            char lastChar = pattern.charAt((pattern.length())-  1);
            StringBuffer sb = new StringBuffer();
           
         
            char ch = (char) in.read();
            
            while (true) {
                //System.out.print(ch);
                if (ch=='B'){
                    k=1;
                }
                if(k==1){
                    line=line+String.valueOf(ch);
                }
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
                if(stringChecker(".*, version.*",line)){

                    

                    String arr[] = new String[2];                 
                    arr = line.substring(28).split(",", 2);
                    this.Submask=arr[0];
                    //System.out.println("" +arr[0]);
                    break;

                }
               

            }


    }
        catch (Exception e) {
        }
        return null;
    }

    public String getSubmask(){
        System.out.println(this.Submask);
        return Submask;
    }

    public boolean stringChecker(String patternString,String line){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);
        boolean matches = matcher.matches();
        return matches;
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            
        }
        catch (Exception e) {
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt + " ");
        }
        catch (Exception e) {
        }
        return null;
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        }
        catch (IOException e) {
        }
    }
    public  String regexChecker(String theRegex,String str2Check){

        Pattern CheckRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = CheckRegex.matcher(str2Check);
        String op="";
        while(regexMatcher.find()){
            if(regexMatcher.group().length() !=0){
      
                op=op+regexMatcher.group().trim();

            }
            
        }
        return op;
    }

    public static void main(String[] args) {
       

            AutomatedTelnetClient telnet = new AutomatedTelnetClient();
       
            System.out.println(telnet.AutomatedTelnet("route-server.ip-plus.net", "rviews", "rviews","10.225.17.159"));
       

    }
}
