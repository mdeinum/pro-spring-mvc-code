package com.apress.prospringmvc.bookstore.basic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.apress.prospringmvc.bookstore.dao.DatabaseDao;

public class BasicUnitTest {

    private List<String> database;
    private DatabaseDao databaseDao;

    @Before
    public void dataSetup() {
        database = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            database.add("test" + i);
        }
        databaseDao = new DatabaseDao(database);
    }

    @Test
    public void testDeleteQuery() {
        assertEquals(1, databaseDao.find("test0").size());
        databaseDao.delete("test0");
        assertEquals(19, database.size());
        assertEquals(0, databaseDao.find("test0").size());
    }

    @Test
    public void testAddQuery() {
        assertEquals(0, databaseDao.find("test20").size());
        databaseDao.add("test20");
        assertEquals(21, database.size());
        assertEquals(1, databaseDao.find("test20").size());
    }

    @Test
    public void testFindQuery() {
        List<String> results = databaseDao.find("2");
        assertEquals(2, results.size());
        for (String result : results) {
            assertTrue(result.equals("test2") || result.equals("test12"));
        }
    }
}
