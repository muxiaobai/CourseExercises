#!/bin/bash

# Author : muxiaobai

#chmod +x ./test.sh  #使脚本具有执行权限
#./test.sh  #执行脚本
#TOMCAT_HOME="/user/local/apache-tomcat-8.0.36"
#LOG_HOME="/data/shelllog/upgrade"
#WAR_HOME="/data/war"
TOMCAT_HOME="/c/apache-tomcat-8.0.36"
TOMCAT_PORT=8080
FILE_NAME=`date "+%Y%m%d%H%M%S"`
LOG_HOME="/c/Users/zhang/Desktop"
LOG_FILE_NAME="$FILE_NAME.log"
LOG_FILE_PATH="$LOG_HOME/$LOG_FILE_NAME"

WAR_HOME="$LOG_HOME"

TIME=`date "+%Y-%m-%d %H:%M:%S"`
#FILE_NAME="upload"
currentTime=`date "+%Y-%m-%d %H:%M:%S"`
echo "脚本开始执行时间：$currentTime" >> "$LOG_FILE_PATH"
startupsh="$TOMCAT_HOME/bin/startup.bat"
shutdownsh="$TOMCAT_HOME/bin/shutdown.bat"
echo "LOG文件路径：$LOG_FILE_PATH" >> "$LOG_FILE_PATH"
echo "启动Tomcat脚本地址：$startupsh" >>"$LOG_FILE_PATH"
echo "关闭tomcat脚本地址：$shutdownsh" >>"$LOG_FILE_PATH"




# if tomcat is run should stop .else skip this  `stop tomcat` step 

cd $TOMCAT_HOME
	echo "$TIME 正在关闭tomcat..." >>"$LOG_FILE_PATH"
	$shutdownsh
	echo "tomcat已经关闭" >>"$LOG_FILE_PATH"
sleep 2s;

# remove old war and file

rm -rf $TOMCAT_HOME/webapps/ROOT*
sleep 2s;
#copy last war 
cd $WAR_HOME
newest_file_of()
{
        ls -t "$@" | head -1
}

echo "newest file of *.war is $(newest_file_of *.war)"
LAST_NAME=$(newest_file_of *.war)

echo "找到最后一次的文件: $LAST_NAME" >> "$LOG_FILE_PATH"
cp $WAR_HOME/$LAST_NAME $TOMCAT_HOME/webapps/ROOT.war

#start tomcat 
cd $TOMCAT_HOME
echo " $TIME 正在启动Tomcat..." >>"$LOG_FILE_PATH"
$startupsh
echo "$TIME 启动Tomcat成功..." >>"$LOG_FILE_PATH"
