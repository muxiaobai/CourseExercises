#!/bin/bash

# Author : muxiaobai
# date : 2018-11-27
# chmod +x ./test.sh  
#         ./test.sh  

echo $1

#===============【change home path】need push ROOT.zip to $UP_BAK_HOME/up dir===============================
UP_BAK_HOME="/thglxt/bak"
TOMCAT_HOME="/thglxt/tomcat-8.5.20"
NGINX_HOME="/usr/local/nginx"
SHELL_NAME="up_roll_2018.sh"

#==========================================upgrad and rollback path==============================================
nginx_start='$NGINX_HOME/bin -s start'
nginx_stop='$NGINX_HOME/bin -s stop'
nginx_reload='$NGINX_HOME/bin -s reload'

startupsh="$TOMCT_HOME/bin/startup.sh"
shutdownsh="$TOMCAT_HOME/bin/shutdown.sh"
WEB_APPS="$TOMCAT_HOME/web-apps/"
WEB_ROOT="$TOMCAT_HOME/web-apps/ROOT"

FILE_NAME=`date "+%Y%m%d%H%M%S"`
currentTime=`date "+%Y-%m-%d %H:%M:%S"`

# upgrade
UP_HOME="$UP_BAK_HOME/up"
UP_ROOT="$UP_HOME/ROOT"
UP_LOG="$UP_HOME/log"
UP_LOG_FILE="$UP_LOG/up_$FILE_NAME.log"

# rollback
BAK_HOME="$UP_BAK_HOME/bak"
BAK_ROOT="$BAK_HOME/ROOT"
BAK_LOG="$BAK_HOME/log"
BAK_LOG_FILE="$BAK_LOG/roll_$FILE_NAME.log"
#==========================================param=================================================================

PARAM=$1
if [ -z $PARAM ];then
 echo 'param is null ,first plase run 【init】,\n and change home,then run【upgrade】 to up the ROOT, or run 【rollback】 to  down the service'
 exit 0;
fi
if [  $PARAM  = 'help'];then
 echo 'param is null ，first plase run 【init】，and change home,then run【upgrade】 to up the ROOT, or run 【rollback】 to  down the service'
 echo ''
 exit 0;
fi

#============================================init=========================================================
if [ $PARAM = 'init' ]; then
echo  '=================init: $currentTime=========================' 

#===================up_home dir ==============================
#文件夹 
if [ ! -d $UP_HOME ];then
mkdir $UP_HOME
else
echo "exit $UP_HOME" 
fi

if [ ! -d $UP_ROOT ];then
mkdir $UP_ROOT
else
echo "exit $UP_ROOT" 
fi

if [ ! -d $UP_LOG ];then
mkdir $UP_LOG
else
echo "exit $UP_LOG" 
fi


#=====================rollback dir============================
#文件夹 
if [ ! -d $BAK_HOME ];then
mkdir $BAK_HOME
else
echo "exit $BAK_HOME"  
fi

if [ ! -d $BAK_ROOT ];then
mkdir $BAK_ROOT
else
echo "exit $BAK_ROOT" 
fi

if [ ! -d $BAK_LOG ];then
mkdir $BAK_LOG
else
echo "exit $BAK_LOG"  
fi 

#==========================================upgrade============================================================
# upgrade
if [ $PARAM = 'upgrade' ]; then


echo  '=================upgrade: $currentTime=========================' 

if [ ! -d $UP_HOME ];then
echo "plase run ./$SHELL_NAME init"
exit 1
fi

touch "$UP_LOAD_FILE"
echo "begin time:$currentTime" >> "$UP_LOG_FILE"

#=================================================
#copy old war 
echo "Old War Name $UP_HOME/$FILE_NAME.tar.gz" >>"$UP_LOG_FILE"
tar -czvf  $UP_ROOT/$FILE_NAME.tar.gz -C $WEB_APPS/ ROOT/
#cp $WEB_APPS/ $BAK_HOME/$FILE_NAME.zip

# stop tomcat
echo "$currentTime stoping tomcat..." >>"$UP_LOG_FILE"
$shutdownsh >> "$UP_LOG_FILE"
echo "$currentTime tomcat stoped" >>"$UP_LOG_FILE"
sleep 2s;

#rm -rf $WEB_APPS/ROOT/

# unzip
echo "unzip $UP_HOME/ROOT.zip " >> "$UP_LOG_FILE"
unzip -o $UP_HOME/ROOT.zip -d $WEB_APPS
sleep 2s;

# ps -ef | grep tomcat 没有的时候可以下面执行

# remove cache
echo " rm -rf $TOMCAT_HOME/work " >> "$UP_LOG_FILE"
rm -rf $TOMCAT_HOME/work/Catalina

#start tomcat 
echo " $currentTime start Tomcat..." >>"$UP_LOG_FILE"
$startupsh >> "$UP_LOG_FILE"
echo "$currentTime Tomcat started..." >>"$UP_LOG_FILE"


# 执行了这些之后，直接退出，因为upgrade 和rollback是只能选择其中一个
exit 0;
fi

#==========================================rollback=================================================================

# rollback
if  [ $PARAM = 'rollback' ];then 

echo '===================rollback: $currentTime=========================' 

#================$check init is run ok,dir=================================

if [ ! -d $BAK_HOME ];then
echo "plase run ./$SHELL_NAME init"
exit 1
fi

#================old_file $UP_ROOT=================================

# rollbakc Log_File

touch "$BAK_LOG_FILE"
echo "begin  rollback time:$currentTime" >> "$BAK_LOG_FILE"


#tar -czvf "$BAK_ROOT/$FILE_NAME.tar.gz" -C $WEB_APPS ROOT/
# last new tar.gz in $UP_ROOT

newest_file_of(){
ls $UP_ROOT -t "$@" | head -1
}

OLD_FILE=$(newest_file_of)
echo "$OLD_FILE last new tar.gz ">> $BAK_LOG_FILE

# 没有upgrade过的没有rollback old_files

#============stop rollback start==========================

# stop tomcat
echo "$currentTime stoping tomcat..." >>"$BAK_LOG_FILE"
$shutdownsh >> "$UP_LOG_FILE"
echo "$currentTime tomcat stoped" >>"$BAK_LOG_FILE"
sleep 2s;

# 移除原来的ROOT运行环境

rm -rf $WEB_APPS/ROOT/
sleep 2s;

# tar -xzvf 
echo  "tar -xzvf $UP_ROOT/$OLD_FILE $WEB_APPS">> "$BAK_LOG_FILE"
tar -xzvf $UP_ROOT/$OLD_FILE $WEB_APPS

# ps -ef | grep tomcat 没有的时候可以下面执行

# remove cache
echo " rm -rf $TOMCAT_HOME/work " >> "$BAK_LOG_FILE"
rm -rf $TOMCAT_HOME/work/Catalina

#start tomcat 
echo " $currentTime start Tomcat..." >>"$BAK_LOG_FILE"
$startupsh >> "$BAK_LOG_FILE"
echo "$currentTime Tomcat started..." >>"$BAK_LOG_FILE"

#============stop rollback start==========================

exit 0;

fi

