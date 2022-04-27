package DAO;

public interface Dao <T>{

    T get(String id,String name);
    void create(T t);
}
