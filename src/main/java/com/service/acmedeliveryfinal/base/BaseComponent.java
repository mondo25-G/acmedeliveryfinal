package com.service.acmedeliveryfinal.base;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public abstract class BaseComponent {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {logger.trace("Loaded {} ", getClass());}

    @PreDestroy
    public void destroy(){logger.trace("{} is about to get Destroyed" , getClass().getName());}


}
