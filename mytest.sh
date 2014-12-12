#!/bin/sh
SERVICE_NAME=test_servi
PATH_TO_JAR=Block.jar
PID_PATH_NAME=tem.txt
case $1 in
    start)
        if [ ! -f $PID_PATH_NAME ]; then
        	
            echo "1"
            nohup java -jar Block.jar  /dev/null &
                        echo $! > $PID_PATH_NAME
            
        else
            echo "1"
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            kill $PID;
            echo "0"
            rm $PID_PATH_NAME
        else
            echo "0"
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -jar $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac 
