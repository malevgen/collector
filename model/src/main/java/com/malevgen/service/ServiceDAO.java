package com.malevgen.service;

import com.malevgen.model.AbstractEntity;

import java.util.Collection;
import java.util.Optional;

public interface ServiceDAO<T extends AbstractEntity> {

    Collection<T> getAll();

    Optional<T> getById(String id);

    Optional<T> getByName(String name);

    Optional<T> save(T entity);

    boolean delete(String id);

}
