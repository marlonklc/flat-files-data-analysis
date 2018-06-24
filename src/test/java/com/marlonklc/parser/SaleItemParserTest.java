package com.marlonklc.parser;

import com.marlonklc.model.SaleItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaleItemParserTest {

    private String validData;
    private String invalidData;

    @Before
    public void startUp() {
        validData = "3-40-3.10";
        invalidData = "3-40";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid() {
        SaleItemParser.of().parse(invalidData);
    }

    @Test
    public void testTotal() {
        SaleItem saleItem = SaleItemParser.of().parse(validData);

        assertEquals(new BigDecimal("124.00"), saleItem.getTotal());
    }

    @Test
    public void testValid() {
        SaleItem saleItem = SaleItemParser.of().parse(validData);

        assertEquals(Long.valueOf(3), saleItem.getId());
        assertEquals(Integer.valueOf(40), saleItem.getQuantity());
        assertEquals(new BigDecimal("3.10"), saleItem.getPrice());
    }
}
