FROM openjdk:17-alpine
COPY COPY user-ms/build/libs/*.jar /app/
WORKDIR /app/
RUN mv /app/*.jar /app/app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/app.jar"]