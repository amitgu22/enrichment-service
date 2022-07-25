package com.data.enrichment.kafka.output;


import com.data.enrichment.model.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OutputProducer {

    private static final Logger logger = LoggerFactory.getLogger(OutputProducer.class);

    @Autowired
    private KafkaTemplate<String, Population> kafkaTemplate;

    @Value(value = "${output.topic.name}")
    private String kafkaTopicName;

    String status = "";


    public void sendMessage(@RequestBody Population population) {


        ListenableFuture<SendResult<String, Population>> future =
                this.kafkaTemplate.send(kafkaTopicName, population);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Population> result) {
                status = "Output Request sent successfully";
                logger.info("## { successfully sent enrichment request to topic name = {}, with offset = {} and request = {} }", result.getProducerRecord().topic(),
                        result.getRecordMetadata().offset(),population.toString()
                        );
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("Failed to send enrichment request = {}, error = {}", population, ex.getMessage());
                status = "Request sending failed";
            }
        });

    }

}




