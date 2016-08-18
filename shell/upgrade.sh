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

# first judge `WAR_HOME` has `SSH.war` if there are then next ,else shell.sh don't run .

# if has SSH.war then rename ROOT.war

echo "重命名文件SSH==>ROOT" >> "$LOG_FILE_PATH"
cp $WAR_HOME/SSH.war $WAR_HOME/ROOT.war
# if tomcat is run should stop .else skip this  `stop tomcat` step 

cd $TOMCAT_HOME
	echo "$TIME 正在关闭tomcat..." >>"$LOG_FILE_PATH"
	$shutdownsh
	echo "tomcat已经关闭" >>"$LOG_FILE_PATH"
sleep 2s;
#copy old war 
echo "Old War Name $FILE_NAME.war" >>"$LOG_FILE_PATH"
cp $TOMCAT_HOME/webapps/ROOT.war $WAR_HOME/$FILE_NAME.war

# remove old war and file

rm -rf $TOMCAT_HOME/webapps/ROOT*
sleep 2s;
#copy new war
echo " $TIME 正在拷贝新项目..." >>"$LOG_FILE_PATH"
cp $WAR_HOME/ROOT.war $TOMCAT_HOME/webapps/
sleep 2s;
#start tomcat 
echo " $TIME 正在启动Tomcat..." >>"$LOG_FILE_PATH"
$startupsh
echo "$TIME 启动Tomcat成功..." >>"$LOG_FILE_PATH"
