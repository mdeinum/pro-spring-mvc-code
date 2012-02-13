package com.apress.prospringmvc.bookstore.basic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BasicUnitTest {

    private List<String> database;
    private Query query;

    @Before
    public void dataSetup() {
        database = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            database.add("test" + i);
        }
        query = new Query(database);
    }

    @Test
    public void testDeleteQuery() {
        query.delete("test0");

        assertEquals(19, database.size());
        for (int i = 0; i < 19; i++) {
            assertEquals("test" + (i + 1), database.get(i));
        }
    }

    @Test
    public void testAddQuery() {
        query.add("test20");

        assertEquals(21, database.size());
        for (int i = 0; i < 21; i++) {
            assertEquals("test" + i, database.get(i));
        }
    }

    @Test
    public void testFindQuery() {
        List<String> results = query.find("2");
        assertEquals(2, results.size());
        for (String result : results) {
            assertTrue(result.equals("test2") || result.equals("test12"));
        }
    }

    private class Query {
        private List<String> database;

        public Query(List<String> database) {
            this.database = database;
        }

        public void delete(String element) {
            database.remove(element);
        }

        public void add(String element) {
            database.add(element);
        }

        public List<String> find(String queryWord) {
            List<String> result = new ArrayList<String>();
            for (String element : database) {
                if (element.contains(queryWord)) {
                    result.add(element);
                }
            }
            return result;
        }
    }
}
