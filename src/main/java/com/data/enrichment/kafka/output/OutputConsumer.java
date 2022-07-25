package com.data.enrichment.kafka.output;

import com.data.enrichment.model.AppConstants;
import com.data.enrichment.model.Population;
import com.data.enrichment.service.impl.EnrichmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OutputConsumer {

    @Autowired
    EnrichmentServiceImpl enrichmentService;


    private static final Logger LOGGER = LoggerFactory.getLogger(OutputConsumer.class);

    @KafkaListener(topics = AppConstants.OUTPUT_TOPIC_NAME,
            groupId = AppConstants.GROUP_ID)
    public void consume(Population message){
        LOGGER.info(String.format("## Consumer level -4 Message received for output-topic-> %s", message));

    }

}
