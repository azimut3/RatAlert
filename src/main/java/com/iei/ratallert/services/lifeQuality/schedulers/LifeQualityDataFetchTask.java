package com.iei.ratallert.services.lifeQuality.schedulers;

import com.iei.ratallert.services.lifeQuality.database.controllers.MidHourStatService;
import com.iei.ratallert.services.lifeQuality.model.LifeQualitySensorDataModel;
import com.iei.ratallert.services.lifeQuality.database.entities.Stat;
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
    MidHourStatService midHourStatService;

    @Override
    public void run() {
        try {
            log.info("Fetching data from arduino controller...");
            RestTemplate restTemplate = new RestTemplate();

            LifeQualitySensorDataModel lifeQualityData = restTemplate
                    .getForObject("http://94.158.155.196:82" + "/lifeQuality", LifeQualitySensorDataModel.class);

            Stat curStat = new Stat(lifeQualityData);
            midHourStatService.save(curStat);

            log.info("Data fetched");
            log.info(lifeQualityData);
        } catch (RestClientException e) {
            log.error("Error on fetching data from arduino controller: " + e.getMessage() + "; " + e.getStackTrace());
        }
    }
}
