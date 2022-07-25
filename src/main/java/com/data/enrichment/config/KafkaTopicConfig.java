package com.data.enrichment.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {




// keeping old reference of yml based configuration
    @Bean
    public NewTopic inputTopic(){
        return TopicBuilder.name("input-topic")
                .build();
    }

    @Bean
    public NewTopic outputTopic(){
        return TopicBuilder.name("output-topic")
                .build();
    }
}
