FROM openjdk:17-jdk-alpine
COPY /target/avro-kafka-shark-1.1.2.jar avro-kafka-shark-1.1.2.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/avro-kafka-shark-1.1.2.jar"]