package se.kth.iv1201.boblaghei.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Abstract service that can be used by all other services.
 */
@Service
abstract public class AbstractService<T> {

    abstract CrudRepository<T, Long> getTRepository();

    T get(long id) {
        CrudRepository<T, Long> repo = getTRepository();
        return repo.findOne(id);
    }

    Iterable<T> getAll() {
       CrudRepository<T, Long> repo = getTRepository();
       return repo.findAll();
    }

    void delete(long id) {
        CrudRepository<T, Long> repo = getTRepository();
        repo.delete(id);
    }

}
