@ECHO OFF
SETLOCAL
CHCP 1252 >NUL
CD /D "%~dp0"

SET JAVA_HOME=C:\dev\java\jdk\jdk8-x86
  "%JAVA_HOME%\bin\java.exe" -jar UtilServeurs.jar

ENDLOCAL
pause