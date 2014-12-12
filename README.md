SSH bot blocker
===
Readme for the correct implementation

1 - Create two .jar files from the given .jar files
      - one .jar file must include following classes (name this as Launch_B.jar)
          
          - map_all.java
          - runner.java
          - settings.java
          - write_ip
          - mail
      
      - other .jar will contain all the other classes (name this as Block.jar)
      
2 - Put both .jar files in the same folder

3 - Then to the same folder add following files
      
      - Blocked_ips.txt
      - Config.txt
      - GeoLiteCity-Blocks.csv
      - GeoLiteCity-Locations.csv
      - Out_log.txt
      - mytest.sh
      
4 - Then you can run the blocker by running Launch_B.jar
      
      note that when the launcher is started the blocker is started automatically you can stop it if you want from using the launcher 
