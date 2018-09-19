#!/bin/bash

# Author : muxiaobai

#chmod +x ./test.sh  
#./test.sh  

TOMCAT_HOME="/thglxt/tomcat-8.5.20"
WEB_APPS="$TOMCAT_HOME/web-apps"
LOG_HOME="/thglxt/log/upgrade"
WAR_HOME="/thglxt/bak/ROOT"
#TOMCAT_HOME="/c/apache-tomcat-8.0.36"
#TOMCAT_PORT=8080
FILE_NAME=`date "+%Y%m%d%H%M%S"`
#LOG_HOME="/c/Users/zhang/Desktop"
LOG_FILE_NAME="$FILE_NAME.log"
LOG_FILE_PATH="$LOG_HOME/$LOG_FILE_NAME"

#WAR_HOME="$LOG_HOME"

TIME=`date "+%Y-%m-%d %H:%M:%S"`
#FILE_NAME="upload"
currentTime=`date "+%Y-%m-%d %H:%M:%S"`
echo "begin time:$currentTime" >> "$LOG_FILE_PATH"
startupsh="$TOMCAT_HOME/bin/startup.sh"
shutdownsh="$TOMCAT_HOME/bin/shutdown.sh"
echo "LOG location:$LOG_FILE_PATH" >> "$LOG_FILE_PATH"
echo "start Tomcat URL:$startupsh" >>"$LOG_FILE_PATH"
echo "start tomcat URL:$shutdownsh" >>"$LOG_FILE_PATH"

# first judge `WAR_HOME` has `SSH.war` if there are then next ,else shell.sh don't run .

# if has SSH.war then rename ROOT.war

#echo "rename Forp-DTH-Product.zip==>ROOT.zip" >> "$LOG_FILE_PATH"
#cp $WAR_HOME/Forp-DTH-Product.zip $WAR_HOME/ROOT.zip
# if tomcat is run should stop .else skip this  `stop tomcat` step 

#copy old war 
echo "Old War Name $FILE_NAME.war" >>"$LOG_FILE_PATH"
cp $WEB_APPS/ROOT.zip $WAR_HOME/$FILE_NAME.zip

# remove old war and file
#rm -rf $TOMCAT_HOME/webapps/ROOT
#sleep 2s;

#copy new war
echo " $TIME copy new war..." >>"$LOG_FILE_PATH"
cp $WAR_HOME/ROOT.zip $WEB_APPS/ROOT.zip

# stop tomcat
echo "$TIME stoping tomcat..." >>"$LOG_FILE_PATH"
$shutdownsh
echo "tomcat stoped" >>"$LOG_FILE_PATH"
sleep 2s;

# unzip
unzip -o ROOT.zip

# remove cache
rm -rf $TOMCAT_HOME/work/Catalina

#start tomcat 
echo " $TIME start Tomcat..." >>"$LOG_FILE_PATH"
$startupsh
echo "$TIME Tomcat started..." >>"$LOG_FILE_PATH"
