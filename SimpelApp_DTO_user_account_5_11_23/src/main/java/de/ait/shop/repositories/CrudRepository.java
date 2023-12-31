package de.ait.shop.repositories;

import de.ait.shop.models.User;

import java.util.List;

public interface CrudRepository <T>{
    T findById(Long id);
    List<T> findAll();
    T save(T model);
    void deleteById(Long id);
    void update(T model);

}
