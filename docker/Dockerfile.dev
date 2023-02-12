FROM amazoncorretto:17-alpine

RUN mkdir -p /save-my-petrol

ADD ./target/*.jar /save-my-petrol/save-my-petrol.jar

CMD java -Dserver.port=8080 -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -jar /save-my-petrol/save-my-petrol.jar
