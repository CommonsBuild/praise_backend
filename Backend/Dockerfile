FROM adoptopenjdk/openjdk11:alpine-jre
RUN addgroup spring && adduser spring -D -G spring 
USER spring:spring
ARG JAR_FILE
COPY build/libs/${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar --spring.profiles.active=local"]