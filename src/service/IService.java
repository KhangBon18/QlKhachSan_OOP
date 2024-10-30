package service;

public interface IService<T> {
    void add(T obj);
    void update(String id, T newObj);
    void delete(String id);
}
