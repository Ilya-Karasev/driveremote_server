package com.example.utils;
import drivermonitor.models.Request;
import drivermonitor.repositories.RequestRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
class TestRequestRepository implements RequestRepository {
    private final Map<Integer, Request> storage = new HashMap<>();
    private int idCounter = 1;

    @Override
    public Request save(Request request) {
        if (request.getId() == null) {
            request.setId(idCounter++);
        }
        storage.put(request.getId(), request);
        return request;
    }

    @Override
    public void deleteById(Integer id) {
        storage.remove(id);
    }

    @Override
    public List<Request> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Request> findByReceiver(Integer receiverId) {
        return storage.values().stream()
                .filter(r -> r.getReceiver().equals(receiverId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Request> findBySender(Integer senderId) {
        return storage.values().stream()
                .filter(r -> r.getSender().equals(senderId))
                .collect(Collectors.toList());
    }

    // Заглушки для ненужных методов
    @Override public Optional<Request> findById(Integer id) { return Optional.ofNullable(storage.get(id)); }
    @Override public boolean existsById(Integer id) { return storage.containsKey(id); }
    @Override public long count() { return storage.size(); }
    @Override public void delete(Request entity) { storage.remove(entity.getId()); }
    @Override public void deleteAll() { storage.clear(); }

    @Override public <S extends Request> List<S> saveAll(Iterable<S> entities) { throw new UnsupportedOperationException(); }
    @Override public void deleteAll(Iterable<? extends Request> entities) { throw new UnsupportedOperationException(); }
    @Override public List<Request> findAllById(Iterable<Integer> ids) { throw new UnsupportedOperationException(); }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Request> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Request> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Request> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override public void deleteAllInBatch() { throw new UnsupportedOperationException(); }

    @Override
    public Request getOne(Integer integer) {
        return null;
    }

    @Override
    public Request getById(Integer integer) {
        return null;
    }

    @Override
    public Request getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Request> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Request> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Request> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Request> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Request> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Request> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Request, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override public void deleteAllById(Iterable<? extends Integer> ids) { throw new UnsupportedOperationException(); }

    @Override
    public List<Request> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Request> findAll(Pageable pageable) {
        return null;
    }
}

