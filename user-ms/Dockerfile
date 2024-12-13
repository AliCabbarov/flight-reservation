FROM openjdk:17-alpine
COPY user-ms-1.0.0.jar.jar /app/
WORKDIR /app/
RUN mv /app/*.jar /app/app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/app.jar"]