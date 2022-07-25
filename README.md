This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using Kafka listner.

The task is to write a simple Java application that does the following: 
1. Read a message from “input_topic” in Kafka. 
2. Enrich the data payload with Country taken from a database 
3. Enrich the data payload with Population taken from a REST/HTTP endpoint 
4. Send the enriched message to “output_topic” in Kafka 

As a pre-requisisye to the demo , we need to have local kafka setup.

Please follow the exact stpes mentioned in below UARL

## https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/

Once setup is done, you can start your spring boot application, which will connect to kafka brooker and will create topic as well.

1. We need to start two seprate services as part of requirement. enrichment-service and population-service

2. We have Open API implementation for kafka publishing of messages

   http://localhost:8080/swagger-ui/index.html
   
3. Test changes /producer/api/sendRequest    

{
  "city": "Berlin",
  "country": "string",
  "population": "string"
}

4. Observed the sequences of message parsing to wards the topics

Received a message contains a population information with city name Berlin, from input-topic topic, 0 partition, and 89823 offset
Hibernate: SELECT * FROM COUNTRY WHERE CITY_NAME=?

{ ## enrichment is completed before sending to output Population(Berlin, 3465000,Germany)

{ successfully sent enrichment request to topic name = output-topic, with offset = 50 and request = Population(Berlin, 3465000,Germany) }



