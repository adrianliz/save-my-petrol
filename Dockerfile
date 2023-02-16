FROM maven:3.8-amazoncorretto-17 AS builder
WORKDIR /save-my-petrol
COPY pom.xml /save-my-petrol
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY /src /save-my-petrol/src
RUN mvn package -Dmaven.test.skip

FROM amazoncorretto:17-alpine
WORKDIR /save-my-petrol
COPY --from=builder /save-my-petrol/target/*.jar /save-my-petrol/save-my-petrol.jar
ARG APP_PORT
ARG APP_ENV
EXPOSE ${APP_PORT}
CMD java -Dspring.profiles.active=${APP_ENV} -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -jar /save-my-petrol/save-my-petrol.jar
