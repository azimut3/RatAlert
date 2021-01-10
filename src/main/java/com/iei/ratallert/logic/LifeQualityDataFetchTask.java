package com.iei.ratallert.logic;

import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.Stat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.TimerTask;

@Service
@Log4j2
public class LifeQualityDataFetchTask extends TimerTask {
    Boolean isInit = true;

    @Autowired
    StatsService statsService;

    @Override
    public void run() {
        try {
            log.info("Fetching data from arduino controller...");
            RestTemplate restTemplate = new RestTemplate();

            LifeQualityData lifeQualityData = restTemplate
                    .getForObject("http://192.168.31.92" + "/lifeQuality", LifeQualityData.class);

            Stat curStat = new Stat(lifeQualityData);
            statsService.save(curStat);

            log.info("Data fetched");
            log.info(lifeQualityData);
        } catch (RestClientException e) {
            log.error("Error on fetching data from arduino controller: " + e.getMessage() + "; " + e.getStackTrace());
        }
    }
}
