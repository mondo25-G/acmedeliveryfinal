package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends BaseEntity> extends BaseComponent implements BaseService<T> {
    public abstract JpaRepository<T, Long> getRepository();

    @Override
    public List<T> createAll(final T... items) {
        return createAll(Arrays.asList(items));
    }

    @Override
    public List<T> createAll(final List<T> items) {
        return getRepository().saveAll(items);
    }

    @Override
    public T create(final T item) {
        logger.trace("Creating {}.", item);
        return getRepository().save(item);
    }

    @Override
    public void update(final T item) {
        logger.trace("Updating {}.", item);
        getRepository().save(item);
    }

    @Override
    public void delete(final T item) {
        final T itemFound = getRepository().getReferenceById(item.getId());
        logger.trace("Deleting {}.", itemFound);
        getRepository().delete(itemFound);
    }

    @Override
    public void deleteById(final Long id) {
        final T itemFound = getRepository().getReferenceById(id);
        logger.trace("Deleting {}.", itemFound);
        getRepository().deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(final T item) {
        logger.trace("Checking whether {} exists.", item);
        return getRepository().existsById(item.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public T get(final Long id) {
        /*
         * T findOne(ID id) (name in the old API) / Optional<T> findById(ID id) (name in the new API) relies on
         * itemManager.find() that performs an item eager loading.
         *
         * T getOne(ID id) relies on itemManager.getReference() that performs an item lazy loading. So to ensure
         * the effective loading of the item, invoking a method on it is required.
         */
        logger.trace("Retrieving item with id {}.", id);
        return getRepository().findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        logger.trace("Retrieving all items.");
        return getRepository().findAll();
    }
}
