package com.data.enrichment.kafka.input;

import com.data.enrichment.model.AppConstants;
import com.data.enrichment.model.Population;
import com.data.enrichment.service.impl.EnrichmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class InputConsumer {

    @Autowired
    EnrichmentServiceImpl enrichmentService;

    private CountDownLatch latch = new CountDownLatch(1);

    private Population payload;

    private static final Logger LOGGER = LoggerFactory.getLogger(InputConsumer.class);

    @KafkaListener(topics = AppConstants.INPUT_TOPIC_NAME,
            groupId = AppConstants.GROUP_ID)
    public void logKafkaMessages(@Payload Population population,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                 @Header(KafkaHeaders.OFFSET) Long offset) {
        LOGGER.info("Received a message contains a population information with city name {}, from {} topic, " +
                "{} partition, and {} offset", population.getCity(), topic, partition, offset);
        payload = population;
        enrichmentService.enrichData(payload);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public Population getPayload() {
        return payload;
    }
}
