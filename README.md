SSH
===

Source Code for SSH bot detecting and blocking.

Features upto 2014/11/11 (V 1.0)

  - Reads the provided log file
  - Find the failed attemts & keep track of them
  - Detect a predifined no of failed attempts from same ip address
  - Block the relevent ip address (Printing the command)
  - Relese the ip after some time passed (Printing the command)
  
Features upto 2014/11/12 (V 1.1)
  
  - Can read the log file continuesly
  
Features upto 2014/8/12  (v 1.2)
  - Unblocking is done in a different single thread
  - Automatically refresh the suspected ips after some time
  - Can map the Blocked Ip addresses
  - Can run as a deamon
  - Has a GUI launcher
