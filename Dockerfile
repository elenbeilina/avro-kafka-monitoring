FROM openjdk:8-jdk-alpine
COPY /target/avro-kafka-shark-1.jar avro-kafka-shark-1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/avro-kafka-shark-1.jar"]