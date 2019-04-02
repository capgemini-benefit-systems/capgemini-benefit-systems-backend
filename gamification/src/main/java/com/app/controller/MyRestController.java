package com.app.controller;

import com.app.model.dto.ProducerDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* This is only example controller that helps creating others.
* This is not a part of final project.
* */

@RestController
@RequestMapping("/api/producer")
public class MyRestController {
    @GetMapping("/all")
    public List<ProducerDto> getAllProducers() {
        RestTemplate restTemplate = new RestTemplate();
        final String URL = "http://localhost:8080/api/producer/all";
        ResponseEntity<ProducerDto[]> response
                = restTemplate.exchange(URL, HttpMethod.GET, null, ProducerDto[].class);
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusCodeValue());
        return Arrays.asList(response.getBody());
    }

    @GetMapping("/{id}")
    public ProducerDto getProducerById(@PathVariable Long id) {
        RestTemplate restTemplate = new RestTemplate();
        final String URL = "http://localhost:8080/api/producer/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", id.toString());

        ResponseEntity<ProducerDto> response
                = restTemplate.exchange(URL, HttpMethod.GET, null, ProducerDto.class, params);
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusCodeValue());
        return response.getBody();
    }

    @GetMapping("/add")
    public ProducerDto addProducer() {
        RestTemplate restTemplate = new RestTemplate();
        final String URL = "http://localhost:8080/api/producer/add";

        HttpEntity<ProducerDto> entity
                = new HttpEntity<>(ProducerDto.builder().name("SII").nationality("PL").budget(BigDecimal.valueOf(100)).build());

        ResponseEntity<ProducerDto> response
                = restTemplate.exchange(URL, HttpMethod.POST, entity, ProducerDto.class);
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusCodeValue());
        return response.getBody();
    }

    @GetMapping("/modify")
    public ProducerDto modifyProducer(@RequestBody ProducerDto producerDto) {
        return null;
    }

    @GetMapping("/delete")
    public ProducerDto deleteProducer(@RequestBody Long id) {
        return null;
    }
}
