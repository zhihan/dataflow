:: Compile scala files
@echo Compile scala files
@echo off
call scalac *.scala

:: Create jar file, remove class files
@echo Create jar file
@echo off
jar cf se.jar MySE\*.class *.class
rd /s/q MySE 
del /f *.class 
copy /y se.jar matlab 