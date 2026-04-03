@echo off
setlocal
set BIN=out\production\bank_activity_simulation_project
set DB_JAR=%USERPROFILE%\Downloads\mysql-connector-java-8.0.28.jar
set CAL_DIR=%USERPROFILE%\Downloads\jcalendar-1.4\lib

:: Add jcalendar jars to the classpath
set CP=%BIN%;%DB_JAR%;%CAL_DIR%\jcalendar-1.4.jar;%CAL_DIR%\jgoodies-common-1.2.0.jar;%CAL_DIR%\jgoodies-looks-2.4.1.jar

echo Starting Bank Management System...
java -cp "%CP%" bank.management.system.Login
pause
