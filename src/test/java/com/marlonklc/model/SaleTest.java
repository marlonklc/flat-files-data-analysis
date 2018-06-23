package com.marlonklc.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaleTest {

    private List<SaleItem> saleItems;

    @Before
    public void setUp() {
        saleItems = Arrays.asList(SaleItem.of(1L, 1, BigDecimal.TEN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArguments() {
        Sale.of(null, null, null);
    }

    @Test
    public void testValidArguments() {
        Sale.of(99L, saleItems, "salesman");
    }

    @Test
    public void testEquals() {
        Sale sale1 = Sale.of(99L, saleItems, "salesman1");
        Sale sale2 = Sale.of(99L, saleItems, "salesman2");

        assertEquals(sale1, sale2);
    }

    @Test
    public void testNotEquals() {
        Sale sale1 = Sale.of(88L, saleItems, "salesman1");
        Sale sale2 = Sale.of(99L, saleItems, "salesman2");

        assertNotEquals(sale1, sale2);
    }

}
