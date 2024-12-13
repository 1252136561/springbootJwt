FROM java:8

COPY *.jar /app.jar

CMD ["--server.port=7777"]

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]
