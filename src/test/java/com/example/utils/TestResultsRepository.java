package com.example.utils;

import drivermonitor.models.Results;
import drivermonitor.repositories.ResultsRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class TestResultsRepository implements ResultsRepository {
    private final Map<Integer, Results> storage = new HashMap<>();
    private int idCounter = 1;

    @Override
    public Results save(Results result) {
        if (result.getId() == null) {
            result.setId(idCounter++);
        }
        storage.put(result.getId(), result);
        return result;
    }

    @Override
    public <S extends Results> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Results> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Results> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Results> findAllById(Iterable<Integer> integers) {
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
    public void delete(Results entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Results> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Results> findByUser_IdOrderByTestDateDesc(Integer userId) {
        return storage.values().stream()
                .filter(r -> r.getUser().getId().equals(userId))
                .sorted(Comparator.comparing(Results::getTestDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Results findFirstByUser_IdOrderByTestDateDesc(Integer userId) {
        return findByUser_IdOrderByTestDateDesc(userId).stream().findFirst().orElse(null);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Results> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Results> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Results> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Results getOne(Integer integer) {
        return null;
    }

    @Override
    public Results getById(Integer integer) {
        return null;
    }

    @Override
    public Results getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Results> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Results> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Results> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Results> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Results> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Results> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Results, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Results> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Results> findAll(Pageable pageable) {
        return null;
    }
}