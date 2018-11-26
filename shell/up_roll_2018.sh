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


#==========================================upgrade============================================================
# upgrade
if [ $PARAM = 'upgrade' ]; then

echo  'upgrade'  >> "$UP_LOG_FILE"
#===================dir ==============================

#文件夹 
if [ ! -d $UP_HOME ];then
mkdir $UP_HOME
else
echo "exit $UP_HOME"  >> "$UP_LOG_FILE"
fi

if [ ! -d $UP_LOG ];then
mkdir $UP_LOG
else
echo "exit $UP_LOG"  >> "$UP_LOG_FILE"
fi

if [ ! -d $UP_ROOT ];then
mkdir $UP_ROOT
else
echo "exit $UP_ROOT"  >> "$UP_LOG_FILE"
fi


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
exit 1;
fi

#==========================================rollback=================================================================

# rollback
if  [ $PARAM = 'rollback' ];then 

echo 'rollback'  >> "$BAK_LOG_FILE"

#=================================================

#文件夹 
if [ ! -d $BAK_HOME ];then
mkdir $BAK_HOME
else
echo "exit $BAK_HOME"  >> "$BAK_LOG_FILE"
fi

if [ ! -d $BAK_LOG ];then
mkdir $BAK_LOG
else
echo "exit $BAK_LOG"  >> "$BAK_LOG_FILE"
fi 

if [ ! -d $BAK_ROOT ];then
mkdir $BAK_ROOT
else
echo "exit $BAK_ROOT" >>$BAK_LOG_FILE
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


