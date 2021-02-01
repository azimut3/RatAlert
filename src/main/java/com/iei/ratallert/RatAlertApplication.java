package com.iei.ratallert;

import com.iei.ratallert.logic.HourlyAvgDataNormalizeTask;
import com.iei.ratallert.logic.LifeQualityDataFetchTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

@Log4j2
@SpringBootApplication
public class RatAlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatAlertApplication.class, args);
    }

    @Autowired
    LifeQualityDataFetchTask lifeQualityDataFetchTask;
    @Autowired
    HourlyAvgDataNormalizeTask hourlyAvgDataNormalizeTask;

    @PostConstruct
    public void servicesInit(){
        new Timer().schedule(lifeQualityDataFetchTask, 5000, 10*1000);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm");
        Integer minutes = Integer.valueOf(dtf.format(LocalDateTime.now()));
        Integer hourlyDelay = (60 - minutes) * 60 * 1000;
        log.info("Delay for hourly stat normalizer: " + hourlyDelay);
        new Timer().schedule(hourlyAvgDataNormalizeTask, hourlyDelay, 60*1000);
    }

}
