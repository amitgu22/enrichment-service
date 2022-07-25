package com.data.enrichment.service.impl;

import com.data.enrichment.controller.EnrichmentController;
import com.data.enrichment.domain.Country;
import com.data.enrichment.kafka.input.InputConsumer;
import com.data.enrichment.kafka.input.InputProducer;
import com.data.enrichment.kafka.output.OutputProducer;
import com.data.enrichment.model.Population;
import com.data.enrichment.repository.EnrichmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrichmentServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnrichmentServiceImpl.class);
    final EnrichmentRepository enrichmentRepository;
    @Value(value = "${rest-population-url}")
    private String restUrl;
    final RestTemplate restTemplate;
    public Population population;
    @Autowired
    OutputProducer producer;
    private static final Logger logger = LoggerFactory.getLogger(EnrichmentController.class);

    @Autowired
    public EnrichmentServiceImpl(EnrichmentRepository enrichmentRepository,@Qualifier("appRestClient") RestTemplate appRestClient) {
        this.enrichmentRepository = enrichmentRepository;
        this.restTemplate = appRestClient;
    }

    public void enrichData(Population population) {




            List<Country> country = enrichmentRepository.findByCityName(population.city);

            population.setCountry(country.stream().map(country1 -> country1.getCountryName()).collect(Collectors.joining()));

            List<Population> populations = getPopulationAPI();

            population.setPopulation(populations.stream().filter(population1 -> population1.getCity().equalsIgnoreCase(population.getCity())
            ).map(population1 -> population1.getPopulation()).collect(Collectors.joining()));

        logger.info("## enrichment is completed before sending to output " + population, population);

//            Optional<Population> populationObj = populations.stream().
//                    filter(population1 -> population1.city.equalsIgnoreCase(population.getCity())).findAny();

            producer.sendMessage(population);


        }






    public List<Population> getPopulationAPI(){
        //restTemplate.exchange()
        //JSON parser object to parse read file

        ResponseEntity<Population[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8081/countryPopulation/",
                        Population[].class);


        return response.getStatusCode().is2xxSuccessful() ? Arrays.stream(response.getBody()).toList() : Collections.emptyList();




    }


}
