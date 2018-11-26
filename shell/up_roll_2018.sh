#!/bin/bash

# Author : muxiaobai


#chmod +x ./test.sh  
#./test.sh  
echo $1
PARAM=$1

#==========================================param=================================================================

INPUT=$1
if [ -z $PARAM ];then
 echo ‘'param is null ，【upgrade】 is up, 【rollback】 is down '
 exit 1;
fi
#==========================================base-path=================================================================

UP_BAK_HOME="/thglxt/bak"
TOMCAT_HOME="/thglxt/tomcat-8.5.20"

#==========================================upgrad and rollback path=================================================================

startupsh="$TOMCT_HOME/bin/startup.sh"
shutdownsh="$TOMCAT_HOME/bin/shutdown.sh"
WEB_APPS="$TOMCAT_HOME/web-apps"

# upgrade
UP_HOME="$UP_BAK_HOME/bak"
UP_ROOT="$UP_HOME/ROOT"
UP_LOG="$UP_HOME/log"

# rollback 
BAK_HOME="$UP_BAK_HOME/bak"
BAK_ROOT="$BAK_HOME/ROOT"
BAK_LOG="$BAK_HOME/log"

FILE_NAME=`date "+%Y%m%d%H%M%S"`

#==========================================upgrade============================================================
# upgrade
if [ $PARAM = 'upgrade' ]; then

echo  'upgrade'
#===================dir ==============================

#文件夹 
if [ ! -d $UP_HOME ];then
mkdir $UP_HOME
else
echo "exit $UP_HOME"
fi

if [ ! -d $UP_LOG ];then
mkdir $UP_LOG
else
echo "exit $UP_LOG"
fi

if [ ! -d $UP_ROOT ];then
mkdir $UP_ROOT
else
echo "exit $UP_ROOT"
fi


#=================================================
#copy old war 
echo "Old War Name $BAK_HOME/$FILE_NAME.tar.gz" >>"$LOG_FILE_PATH"
tar -czvf  $BAK_HOME/$FILE_NAME.tar.gz -C $WEB_APPS/ ROOT/
#cp $WEB_APPS/ $BAK_HOME/$FILE_NAME.zip



# stop tomcat
echo "$TIME stoping tomcat..." >>"$LOG_FILE_PATH"
$shutdownsh >> "$LOG_FILE_PATH"
echo "tomcat stoped" >>"$LOG_FILE_PATH"
sleep 2s;

#rm -rf $WEB_APPS/ROOT/

# unzip
echo "unzip $BAK_HOME/ROOT.zip " >> "$LOG_FILE_PATH"
unzip -o $BAK_HOME/ROOT.zip -d $WEB_APPS
sleep 2s;
# remove cache
echo " rm -rf $TOMCAT_HOME/work " >> "$LOG_FILE_PATH"
rm -rf $TOMCAT_HOME/work/Catalina

#start tomcat 
echo " $TIME start Tomcat..." >>"$LOG_FILE_PATH"
$startupsh >> "$LOG_FILE_PATH"
echo "$TIME Tomcat started..." >>"$LOG_FILE_PATH"


# 执行了这些之后，直接退出，因为upgrade 和rollback是只能选择其中一个
exit 1;
fi

#==========================================rollback=================================================================

# rollback
if  [ $PARAM = 'rollback' ];then 

echo 'rollback'

#=================================================

#文件夹 
if [ ! -d $BAK_HOME ];then
mkdir $BAK_HOME
else
echo "exit $BAK_HOME"
fi

if [ ! -d $BAK_LOG ];then
mkdir $BAK_LOG
else
echo "exit $BAK_LOG"
fi

if [ ! -d $BAK_ROOT ];then
mkdir $BAK_ROOT
else
echo "exit $BAK_ROOT"
fi

#=================================================

# rollbakc Log_File

BAK_LOG_FILE=$BAK_LOG/$FILE_NAME.log

currentTime=`date "+%Y-%m-%d %H:%M:%S"`
echo $BAK_LOG_FILE
touch $BAK_LOG_FILE

#tar -czvf "$BAK_ROOT/$FILE_NAME.tar.gz" -C $WEB_APPS ROOT/

newest_file_of(){
ls $UP_ROOT -t "$@" | head -1
}
echo "$(newest_file_of)  last new tar.gz ">> $BAK_LOG_FILE
OLD_FILE=$(newest_file_of)

# 没有upgrade过的没有rollback old_files

#=================================================

#============stop  start==========================

exit 1;

fi


