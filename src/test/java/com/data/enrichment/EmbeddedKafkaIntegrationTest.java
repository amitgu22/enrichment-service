// package com.data.enrichment;


// import com.data.enrichment.kafka.input.InputConsumer;
// import com.data.enrichment.kafka.input.InputProducer;
// import org.junit.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.test.context.EmbeddedKafka;
// import org.springframework.test.annotation.DirtiesContext;
// import org.springframework.test.context.ActiveProfiles;

// import java.util.concurrent.TimeUnit;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// @SpringBootTest
// @DirtiesContext
// @ActiveProfiles(value = "test")
// @EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=http://localhost:9092", "port=9092" })
// public class EmbeddedKafkaIntegrationTest {

//         @Autowired
//         public KafkaTemplate<String, String> template;

//         @Autowired
//         private InputConsumer consumer;

//         @Autowired
//         private InputProducer producer;

//         @Value(value = "${test.topic}")
//         private String topic;

//         @BeforeEach
//         void setup() {
//             consumer.resetLatch();

//         }

//         @Test
//         public void givenEmbeddedKafkaBroker_whenSendingWithDefaultTemplate_thenMessageReceived() throws Exception {
//             String data = "Sending with default template";

//             template.send(topic, data);

//             boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
//             assertTrue(messageConsumed);
//             //assertThat(consumer.getPayload(), Population.builder().city("Berlin").build());
//         }



//     }
