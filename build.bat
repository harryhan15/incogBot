@echo off
call mvn clean package
robocopy "F:/Programmin/incog/target" "F:/Programmin/incog/exec"