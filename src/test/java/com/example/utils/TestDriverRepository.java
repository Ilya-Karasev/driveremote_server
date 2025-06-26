package com.example.utils;

import drivermonitor.models.Driver;
import drivermonitor.repositories.DriverRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

class TestDriverRepository implements DriverRepository {
    private final Map<Integer, Driver> storage = new HashMap<>();

    @Override
    public <S extends Driver> S save(S entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public <S extends Driver> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Driver> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Driver> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Driver entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Driver> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends Driver> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Driver> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Driver> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Driver getOne(Integer integer) {
        return null;
    }

    @Override
    public Driver getById(Integer integer) {
        return null;
    }

    @Override
    public Driver getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Driver> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Driver> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Driver> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Driver> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Driver> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Driver> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Driver, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Driver> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Driver> findAll(Pageable pageable) {
        return null;
    }
}

