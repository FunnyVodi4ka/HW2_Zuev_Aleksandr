package myApp.dao;

import java.util.List;

public interface HibernateDao <E> {
    public E getById(int id);
    public void save(E entity);
    public void update(E entity);
    public void delete(E entity);
    public List<E> getAll();
}
