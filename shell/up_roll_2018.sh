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

#==========================================upgrade=================================================================
# upgrade
if [ $PARAM = 'upgrade' ]; then

echo  'upgrade'


exit 1;
fi

#==========================================upgrade=================================================================

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


