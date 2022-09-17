package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{

    private Store loggedStore;
    private final StoreRepository storeRepository;
    @Override
    public JpaRepository<Store, Long> getRepository() {return storeRepository;}


    @Override
    public Store getLazy(Long id) {
            Optional<Store> store = storeRepository.getLazy(id);
            if (store.isPresent()) {
                return store.get();
            }
            throw new NoSuchElementException(String.format("There was no store found matching id %d.", id));
    }

    @Override
    public List<Store> getLazyAll() {
        return storeRepository.getLazyAll();
    }
}
