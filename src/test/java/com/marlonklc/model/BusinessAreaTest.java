package com.marlonklc.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BusinessAreaTest {

    private String stringBusinessArea1;
    private String stringBusinessArea2;

    @Before
    public void startUp() {
        stringBusinessArea1 = "technology";
        stringBusinessArea2 = "infrastructure";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        BusinessArea.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        BusinessArea.of(" ");
    }

    @Test
    public void testValid() {
        BusinessArea businessArea = BusinessArea.of(stringBusinessArea1);

        assertEquals(stringBusinessArea1, businessArea.getValue());
    }

    @Test
    public void testEquals() {
        BusinessArea businessArea1 = BusinessArea.of(stringBusinessArea1);
        BusinessArea businessArea2 = BusinessArea.of(stringBusinessArea1);

        assertEquals(businessArea1, businessArea2);
    }

    @Test
    public void testNotEquals() {
        BusinessArea businessArea1 = BusinessArea.of(stringBusinessArea1);
        BusinessArea businessArea2 = BusinessArea.of(stringBusinessArea2);

        assertNotEquals(businessArea1, businessArea2);
    }
}
