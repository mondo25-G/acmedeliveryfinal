package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ContentSampleCreator extends BaseComponent implements CommandLineRunner {
    private final StoreService storeService;

    //@formatter: off
    //create test. succeeds

    @Override
    public void run(String... args) throws Exception {

    }
    //@formatter: on

}
