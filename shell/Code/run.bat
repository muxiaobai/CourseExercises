color 70
echo off
:begin
cls
echo ****************************************************************************
echo * 1:生成更新数据 this       					*
echo * 2:生成更新数据 金元证券（jy_zq）			        *
echo * 3:生成更新数据 前海证券（qh_zq）			        *
echo * 4: 华创更新  （hc_zq）D:/tomcat-8.5.32-8095/webapps/ROOT                                                   		*
echo * a:生成更新数据 ALL 全部                               		*
echo * u:更新数据                                            		*
echo * 0:退出                                                		*
echo ****************************************************************************

set /p in=
if %in%==1 goto :1
if %in%==2 goto :2
if %in%==3 goto :3
if %in%==4 goto :4
if %in%==u goto :UPDATE
if %in%==a goto :ALL
if %in%==0 goto :end

:1
start createUpdate.bat this
goto :begin

:2
start createUpdate.bat jy_zq
goto :begin

:3
start createUpdate.bat qh_zq
goto :begin

:4
start createUpdate.bat hc_zq
goto :begin

:ALL
start createUpdate.bat
goto :begin

:UPDATE
start runUpdate.bat
goto :begin
:3
goto :end
:end
exit