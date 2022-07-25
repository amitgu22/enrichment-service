package com.data.enrichment.kafka.input;



import com.data.enrichment.model.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@Component
public class InputProducer {

    private static final Logger logger = LoggerFactory.getLogger(InputProducer.class);

    @Autowired
    private KafkaTemplate<String, Population> kafkaTemplate;


    @Value(value = "${input.topic.name}")
    private String kafkaInputTopicName;

    String status = "";


    public void sendMessage(@RequestBody Population population) {
        logger.info("Producer sending enrichment request {}", population);

        ListenableFuture<SendResult<String, Population>> future =
                this.kafkaTemplate.send(kafkaInputTopicName, population);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Population> result) {

                logger.info("Producer level 1 - successfully sent enrichment request for city = {}, with offset = {}", population,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("## Producer level 1 - Failed to send enrichment request = {}, error = {}", population, ex.getMessage());
                status = "Request sending failed";
            }
        });

    }

}




