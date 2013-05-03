rm *.class
javac -cp . *.java
"C:\Program Files\Java\jdk1.7.0_11\bin\rmic.exe" -classpath . ChatServer ChatClient

Pause;