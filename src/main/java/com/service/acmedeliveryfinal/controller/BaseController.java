package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.BaseEntity;
import com.service.acmedeliveryfinal.service.BaseService;

public abstract class BaseController<T extends BaseEntity> extends BaseComponent {

    protected abstract BaseService<T> getBaseService();


}
