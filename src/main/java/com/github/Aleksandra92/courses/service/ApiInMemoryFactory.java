package com.github.Aleksandra92.courses.service;

import com.github.Aleksandra92.courses.service.impl.memory.GroupApiInMemoryImpl;
import com.github.Aleksandra92.courses.service.impl.memory.StudentApiInMemoryImpl;

/**
 * Author: Aleksandra Perova. Created on 31.03.2015.
 */
public class ApiInMemoryFactory {
    private static GroupApiInMemoryImpl groupApi;
    private static StudentApiInMemoryImpl studentApi;

    public static synchronized GroupApi getGroupApiInstance() {
        if (groupApi == null) {
            groupApi = new GroupApiInMemoryImpl();
        }
        return groupApi;
    }

    public static synchronized StudentApi getStudentApiInstance() {
        if (studentApi == null) {
            studentApi = new StudentApiInMemoryImpl();
        }
        return studentApi;
    }
}
