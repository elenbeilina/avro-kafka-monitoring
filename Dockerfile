FROM openjdk:17-jdk-alpine
COPY /target/avro-kafka-monitoring-*.jar avro-kafka-monitoring-*.jar
ENTRYPOINT ["java","-Dspring.profiles.active=test","-jar","/avro-kafka-monitoring-*.jar"]