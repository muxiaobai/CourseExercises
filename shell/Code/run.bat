color 70
echo off
:begin
cls
echo ****************************************************************************
echo * 1:���ɸ������� this       					*
echo * 2:���ɸ������� ��Ԫ֤ȯ��jy_zq��			        *
echo * 3:���ɸ������� ǰ��֤ȯ��qh_zq��			        *
echo * 4: ��������  ��hc_zq��D:/tomcat-8.5.32-8095/webapps/ROOT                                                   		*
echo * a:���ɸ������� ALL ȫ��                               		*
echo * u:��������                                            		*
echo * 0:�˳�                                                		*
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