FROM java:8-jre
WORKDIR /app
ADD target/*.jar app.jar
VOLUME /tmp
EXPOSE 9080
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]