#!/bin/bash

# Author : muxiaobai

#chmod +x ./test.sh  
#./test.sh  
#set fileformat = unix
UP_BAK_HOME="/thglxt/bak"
TOMCAT_HOME="/thglxt/tomcat-8.5.20"


BAK_HOME="$UP_BAK_HOME/bak"
UP_HOME="$UP_BAK_HOME/upgrade"
BAK_LOG_PATH="$BAK_HOME/log"
BAK_ROOT="$BAK_HOME/ROOT"
WEB_APPS="$TOMCAT_HOME/web-apps"

# mkdir path
if [ ! -d "$BAK_ROOT" ];then
mkdir $BAK_ROOT
else
echo "$BAK_ROOT 文件夹已经存在"
fi

if [ ! -d "$BAK_LOG" ];then
mkdir $BAK_LOG
else
echo " $BAK_LOG 文件夹已经存在"
fi

currentTime=`date "+%Y-%m-%d %H:%M:%S"`
LOG_NAME=`date "+%Y%m%d%H%M%S"`
BAK_LOG_FILE_NAME="$LOG_NAME.log"
BAK_FILE_PATH="$BAK_LOG/$BAK_LOG_FILE_NAME"

echo "begin time :$currentTime" >> "$BAK_LOG_FILE_PATH"
startupsh="$TOMCAT_HOME/bin/startup.sh"
shutdownsh="$TOMCAT_HOME/bin/shutdown.sh"
echo "LOG location:$LOG_FILE_PATH" >> "$BAK_LOG_FILE_PATH"
echo "start Tomcat URL:$startupsh" >>"$BAK_LOG_FILE_PATH"
echo "stop Tomcat URL:$shutdownsh" >>"$BAK_LOG_FILE_PATH"

# tar running ROOT
tar -czvf 
#copy last tar.gz 
newest_file_of()
{
        ls $BAK_HOME -t "$@" | head -1
}

echo "newest file of *.war is $(newest_file_of *.war)"
LAST_NAME=$(newest_file_of *.war)

echo "last file: $LAST_NAME" >> "$LOG_FILE_PATH"



# if tomcat is run should stop .else skip this  `stop tomcat` step 

echo "$currentTime stop Tomcat..." >>"$LOG_FILE_PATH"
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
echo " $currentTime start Tomcat..." >>"$LOG_FILE_PATH"
$startupsh
echo "$currentTime Tomcat started..." >>"$LOG_FILE_PATH"

