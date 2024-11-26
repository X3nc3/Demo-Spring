package fr.diginamic.hello.dao;

import java.util.List;

public interface IDAO<T> {
    List<T> findAll();
}
