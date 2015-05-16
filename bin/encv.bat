@echo off

if "%ENCV_HOME%"=="" goto noSetHome
if not exist %ENCV_HOME%\lib\encv.jar goto noSetHome

java -classpath "%ENCV_HOME%\lib\encv.jar;%ENCV_HOME%\lib\commons-cli-1.3.jar" main.EnCv %1 %2 %3 %4 %5 %6 %7 %8 %9
goto end

:noSetHome
echo ENCV_HOME is set incorrectly or ant could not be located. Please set ENCV_HOME.
goto end

:end
@echo on
