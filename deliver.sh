#!/usr/bin bash
chmod +x target/example-app-${BUILD_NUMBER}.jar
java -jar target/example-app-${BUILD_NUMBER}.jar
