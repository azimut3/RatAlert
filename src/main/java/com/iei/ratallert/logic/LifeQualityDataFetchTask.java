package com.iei.ratallert.logic;

import java.util.TimerTask;

public class LifeQualityDataFetchTask extends TimerTask {


    @Override
    public void run() {
        Foo foo = restTemplate
                .getForObject(fooResourceUrl + "/1", Foo.class);
    }
}
