package com.example.servicevoiture;

import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceVoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVoitureApplication.class, args);
    }
    @Bean
    CommandLineRunner loadData(VoitureRepository voitureRepository) {
        return args -> {

            voitureRepository.save(new Voiture(null, "Toyota", "A 123 45", "Corolla", 1L));
            voitureRepository.save(new Voiture(null, "Renault", "B 987 65", "Clio", 1L));
            voitureRepository.save(new Voiture(null, "Peugeot", "C 456 78", "208", 2L));
            voitureRepository.save(new Voiture(null, "Mercedes", "D 222 11", "C-Class", 3L));
            voitureRepository.save(new Voiture(null, "BMW", "E 555 22", "320i", 2L));

            System.out.println("✔ Fake voiture dataset loaded!");
        };
    }
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        restTemplate.setRequestFactory(requestFactory);

        return restTemplate;
    }

}
