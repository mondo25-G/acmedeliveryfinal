package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T> {
    public abstract JpaRepository<T, Long> getRepository();

}
