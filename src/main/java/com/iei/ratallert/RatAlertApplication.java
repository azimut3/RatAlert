package com.iei.ratallert;

import com.iei.ratallert.logic.LifeQualityDataFetchTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Timer;

@SpringBootApplication
public class RatAlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatAlertApplication.class, args);
    }

    @Autowired
    LifeQualityDataFetchTask lifeQualityDataFetchTask;

    @PostConstruct
    public void servicesInit(){
        new Timer().schedule(lifeQualityDataFetchTask, 5000, 5000);
    }

}
