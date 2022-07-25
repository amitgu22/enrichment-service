package com.data.enrichment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import java.util.Collections;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@OpenAPIDefinition(info = @Info(title = "Enrichment service API", version = "1.0", description = "Enrichment fullfilment for country Information"))
public class EnrichmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrichmentServiceApplication.class, args);
	}

	@Bean(name = "appRestClient")
	public RestTemplate getRestClient() {
		RestTemplate restClient = new RestTemplate(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

		// Add one interceptor like in your example, except using anonymous class.
		restClient.setInterceptors(Collections.singletonList((request, body, execution) -> execution.execute(request, body)));

		return restClient;
	}
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		return new InternalResourceViewResolver();
	}
}
