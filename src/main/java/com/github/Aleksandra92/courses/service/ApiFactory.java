package com.github.Aleksandra92.courses.service;


/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class ApiFactory {

    public static synchronized GroupApi getGroupApiInstance() {
        return ApiInMemoryFactory.getGroupApiInstance();
    }

    public static synchronized StudentApi getStudentApiInstance() {
        return ApiInMemoryFactory.getStudentApiInstance();
    }
}
