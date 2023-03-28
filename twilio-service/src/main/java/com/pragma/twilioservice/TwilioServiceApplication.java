package com.pragma.twilioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.FeignClient;

@EnableEurekaClient
@FeignClient
@SpringBootApplication
public class TwilioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwilioServiceApplication.class, args);
    }

}
