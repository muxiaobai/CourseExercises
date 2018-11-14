#!/bin/bash

# Author : muxiaobai

#chmod +x ./test.sh  
#./test.sh  
#set fileformat = unix
TOMCAT_HOME="/thglxt/tomcat-8.5.20"
WEB_APPS="$TOMCAT_HOME/web-apps"
LOG_HOME="/data/log/bak"
WAR_HOME="/thglxt/bak/ROOT"
#TOMCAT_HOME="/c/apache-tomcat-8.0.36"
TOMCAT_PORT=8080
FILE_NAME=`date "+%Y%m%d%H%M%S"`
#LOG_HOME="/c/Users/zhang/Desktop"
LOG_FILE_NAME="$FILE_NAME.log"
LOG_FILE_PATH="$LOG_HOME/$LOG_FILE_NAME"

#WAR_HOME="$LOG_HOME"

TIME=`date "+%Y-%m-%d %H:%M:%S"`
#FILE_NAME="upload"
currentTime=`date "+%Y-%m-%d %H:%M:%S"`
echo "begin time :$currentTime" >> "$LOG_FILE_PATH"
startupsh="$TOMCAT_HOME/bin/startup.sh"
shutdownsh="$TOMCAT_HOME/bin/shutdown.sh"
echo "LOG location:$LOG_FILE_PATH" >> "$LOG_FILE_PATH"
echo "start Tomcat URL:$startupsh" >>"$LOG_FILE_PATH"
echo "stop Tomcat URL:$shutdownsh" >>"$LOG_FILE_PATH"


#copy last war 
cd $WAR_HOME
newest_file_of()
{
        ls -t "$@" | head -1
}

echo "newest file of *.war is $(newest_file_of *.war)"
LAST_NAME=$(newest_file_of *.war)

echo "last file: $LAST_NAME" >> "$LOG_FILE_PATH"
cp $WAR_HOME/$LAST_NAME $TOMCAT_HOME/webapps/ROOT.war



# if tomcat is run should stop .else skip this  `stop tomcat` step 

echo "$TIME stop Tomcat..." >>"$LOG_FILE_PATH"
$shutdownsh
echo "tomcat stoped" >>"$LOG_FILE_PATH"
sleep 2s;


# remove ROOT
rm -rf $WEB_APPS/ROOT

# remove cache
rm -rf $TOMCAT_HOME/work/Catalina

# unzip
unzip -o ROOT.zip

#start tomcat 
cd $TOMCAT_HOME
echo " $TIME start Tomcat..." >>"$LOG_FILE_PATH"
$startupsh
echo "$TIME Tomcat started..." >>"$LOG_FILE_PATH"

