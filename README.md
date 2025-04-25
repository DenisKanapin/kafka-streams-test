# kafka-streams-test
This repository contains test Spring and Kafka application 


**High level architecture diagram**
![Diagram](https://github.com/DenisKanapin/kafka-streams-test/blob/main/Diagram_testcode.png?raw=true)



**How to run the app**
1. docker compose up -d
2. Create debezium connector: curl -s -S -XPOST -H Accept:application/json -H Content-Type:application/json http://localhost:8083/connectors/ -d @debezium-config.json
3. Run maven plugin avro-maven-plugin(Java already generated and saved in repository, just in case)
4. Run Spring Boot app KafkaDemoApplication

**Some transactional behavior can be tested by adding different prices for products**
Look at FailedMessageKafkaService::sendDeadMessage

**TO DO**
1. Reduce steps above to 1 step with docker-compose.yml
2. Add more multithreading functionality in code 
3. Improve volumes storing in docker compose
4. Create Spring MVC layer with some UI to create/delete connectors and monitor Kafka topics
5. Add some metrics
