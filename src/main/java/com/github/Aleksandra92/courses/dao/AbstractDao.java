package com.github.Aleksandra92.courses.dao;

import com.github.Aleksandra92.courses.beans.Entity;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public interface AbstractDao<T extends Entity> {
    /**
     * Добавить обьект.
     * @param entity Добовляемый обьект.
     */
    void saveOrUpdate(T entity);

    /**
     *  Удалить объект по id.
     * @param id Id обьекта.
     */
    void delete(Long id);

    /**
     * Удаляет объект.
     * @param entity Обьект.
     */
    void delete(T entity);

    /**
     *
     * @param id Id обьекта.
     * @return Возвращает Id .
     */
    T get(Long id);

    /**
     * Получаем список.
     * @return Список.
     */
    List<T> getAll();

}
