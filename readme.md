## App for adding shark to aquarium.
This app is producing  mako shark to the aquarium by rest endpoint.
Shark is producing by avro kafka producer to kafka broker using schema registry. Record shema is located in `shark.avsc` file.
Record object is generated by maven.

---
###Environment:
- docker env can be found in `docker-compose.yaml` file
- control-center was added to env for cluster monitoring, \
  so cluster state can be checked by `http://localhost:9021/clusters` url.

---
###Test scenario:

 1. Build project:
    ```
    mvn clean package
    ```
 2. Build docker image:
    ```
    docker build -t avro-kafka-aquarium .
    ```
 3. Ran docker environment:
    ```
    docker compose up -d
    ```
 4. Add shark to aquarium:
    ```
    sh add-shark.sh
    ```
    
Result can be checked either by log in console or by viewing messages in aquarium topic in control center.