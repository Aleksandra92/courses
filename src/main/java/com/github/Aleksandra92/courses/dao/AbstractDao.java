package com.github.Aleksandra92.courses.dao;

import com.github.Aleksandra92.courses.beans.Entity;

import java.util.List;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public interface AbstractDao<T extends Entity, E extends Exception> {

    /**
     * Добавить обьект.
     * @param entity Добовляемый обьект.
     */
    void saveOrUpdate(T entity) throws E;

    /**
     *  Удалить объект по id.
     * @param id Id обьекта.
     */
    void delete(Long id) throws E;

    /**
     * Удаляет объект.
     * @param entity Обьект.
     */
    void delete(T entity) throws E;

    /**
     *
     * @param id Id обьекта.
     * @return Возвращает Id .
     */
    T get(Long id) throws E;

    /**
     * Получаем список.
     * @return Список.
     */
    List<T> getAll() throws E;

    void deleteAll() throws E;

    void addAll(List<T> entity) throws E;
}
