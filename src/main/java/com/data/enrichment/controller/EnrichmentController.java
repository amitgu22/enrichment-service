package com.data.enrichment.controller;

import com.data.enrichment.exception.InvalidRequestException;
import com.data.enrichment.exception.ErrorCode;
import com.data.enrichment.kafka.input.InputProducer;
import com.data.enrichment.model.Population;
import com.data.enrichment.service.impl.EnrichmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class EnrichmentController {


    final
    EnrichmentServiceImpl enrichmentService;

    String status = "";


    private static final Logger logger = LoggerFactory.getLogger(EnrichmentController.class);


    private final InputProducer kafkaProducer;

    public EnrichmentController(InputProducer kafkaProducer, EnrichmentServiceImpl enrichmentService) {
        this.kafkaProducer = kafkaProducer;
        this.enrichmentService = enrichmentService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/api/sendRequest")
    public ResponseEntity<String> sendMessage(@RequestBody Population population) throws InvalidRequestException {
        logger.info(" Message Received : " + population.toString());
        if(population.getCity() == null || population.getCity().isEmpty()){
            throw new InvalidRequestException("Validation failed for incoming request with error message {}" + ErrorCode.INVALID_REQUEST);
        }
        this.kafkaProducer.sendMessage(population);
        return ResponseEntity.ok(status);


    }

}




