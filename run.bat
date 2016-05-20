@echo off
for /f "delims=" %%i in ('"mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep -v '\['"') do set version=%%i
set "val1=F:\Programmin\incog\exec\IncognitoBot-"
set "val2=.jar"
set "tar=%val1%%version%%val2%"

echo %tar%

java -jar %tar%