package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{

    private Store loggedStore;
    private final StoreRepository storeRepository;
    @Override
    public JpaRepository<Store, Long> getRepository() {return storeRepository;}

    @Override
    public Store getStore( final Long id){
        loggedStore = storeRepository.findById(id).get();
        return loggedStore;
    }

    @Override
    public List<Store> getStoresByName(String name){
        return storeRepository.getStoresByName(name);
    }

    @Override
    public List<Store> getStoresByCategory(StoreCategory category){
        return storeRepository.getStoresByCategory(category.toString());
    }
}
