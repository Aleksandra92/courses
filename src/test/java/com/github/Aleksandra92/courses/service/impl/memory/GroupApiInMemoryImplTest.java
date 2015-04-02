package com.github.Aleksandra92.courses.service.impl.memory;

import com.github.Aleksandra92.courses.service.GroupApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: Aleksandra Perova. Created on 01.04.2015.
 */
public class GroupApiInMemoryImplTest {

    private GroupApi groupApi;

    @Before
    public void setUp() {
        this.groupApi = new GroupApiInMemoryImpl();
    }

    @Test
    public void testGetGroups() throws Exception {
        this.groupApi.getGroups();
        Assert.assertEquals(2, this.groupApi.getGroups().size());
    }

}