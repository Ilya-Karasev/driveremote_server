package com.example.utils;

import drivermonitor.models.Manager;
import drivermonitor.repositories.ManagerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import java.util.*;
import java.util.function.Function;

class TestManagerRepository implements ManagerRepository {
    private final Map<Integer, Manager> data = new HashMap<>();

    @Override
    public Optional<Manager> findById(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Manager save(Manager manager) {
        data.put(manager.getId(), manager);
        return manager;
    }

    @Override
    public <S extends Manager> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Manager> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Manager> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        data.remove(id);
    }

    @Override
    public void delete(Manager entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Manager> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Manager> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Manager> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Manager> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Manager getOne(Integer integer) {
        return null;
    }

    @Override
    public Manager getById(Integer integer) {
        return null;
    }

    @Override
    public Manager getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Manager> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Manager> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Manager> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Manager> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Manager> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Manager> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Manager, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Manager> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Manager> findAll(Pageable pageable) {
        return null;
    }
}
