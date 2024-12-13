FROM openjdk:17-alpine
RUN cd
COPY /home/runner/work/flight-reservation/flight-reservation/user-ms/build/libs/*.jar /app/
WORKDIR /app/
RUN mv /app/*.jar /app/app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/app.jar"]