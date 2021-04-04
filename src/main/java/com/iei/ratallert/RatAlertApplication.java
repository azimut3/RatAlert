package com.iei.ratallert;

import com.iei.ratallert.services.lifeQuality.schedulers.HourlyAvgDataNormalizeTask;
import com.iei.ratallert.services.lifeQuality.schedulers.LifeQualityDataFetchTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        //new Timer().schedule(lifeQualityDataFetchTask, 5000L, 10L*60L*1000L);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm");
        Integer minutes = Integer.valueOf(dtf.format(LocalDateTime.now()));
        Long hourlyDelay = (60L - minutes) * 60L * 1000L;
        log.info("Delay for hourly stat normalizer: " + hourlyDelay);

       //new Timer().schedule(hourlyAvgDataNormalizeTask, hourlyDelay, 60L*60L*1000L);
    }

}
