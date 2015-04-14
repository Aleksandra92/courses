package com.github.Aleksandra92.courses.dao.impl.memory;

/**
 * Author: Aleksandra Perova. Created on 13.04.2015.
 */
public class Counter {

    private static long counter;

    public static Long getNextId() {
        return counter++;
    }
}
