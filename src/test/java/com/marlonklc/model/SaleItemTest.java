package com.marlonklc.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaleItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArguments() {
        SaleItem.of(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLessThanZero() {
        SaleItem.of(99L, Integer.MIN_VALUE, BigDecimal.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPriceEqualsZero() {
        SaleItem.of(99L, Integer.MAX_VALUE, BigDecimal.ZERO);
    }

    @Test
    public void testValidArguments() {
        SaleItem.of(99L, Integer.MAX_VALUE, BigDecimal.TEN);
    }

    @Test
    public void testTotalSaleItem() {
        SaleItem saleItem = SaleItem.of(99L, 10, BigDecimal.TEN);

        assertEquals(saleItem.getTotal(), new BigDecimal(100));
    }

    @Test
    public void testEquals() {
        SaleItem saleItem1 = SaleItem.of(99L, 1, BigDecimal.ONE);
        SaleItem saleItem2 = SaleItem.of(99L, 10, BigDecimal.TEN);

        assertEquals(saleItem1, saleItem2);
    }

}
