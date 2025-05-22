@echo off
echo Compiling Java files...
javac -cp ".;sqlite-jdbc-3.49.1.0.jar" *.java

echo Running the game...
java -cp ".;sqlite-jdbc-3.49.1.0.jar" MainFrame

pause
