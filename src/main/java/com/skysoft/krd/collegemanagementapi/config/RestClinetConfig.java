package com.skysoft.krd.collegemanagementapi.config;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClinetConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;
    @Bean
    @Qualifier("EmployeeClientImpl")
    public RestClient getEmployeeServiceRestClinet(){

        return  RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(req,res)->{
                    System.out.println(new String(res.getBody().readAllBytes()));
                    throw  new RuntimeException("server error occur");
                })
                .build();
    }
}
