package main.java.exchanger.service;

import main.java.exchanger.dto.Dto;

import java.util.Collection;

/**
 * Created by root on 27.05.17.
 */
public interface Service<K, T extends Dto> {

    T get(K id);

    Collection<T> getAll();

//    void delete(K id);

    void deleteAll(Collection<T> elements);

//    K save(T element);

    Collection<T> saveAll(Collection<T> elements);
}

