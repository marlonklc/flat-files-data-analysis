package com.marlonklc.parser;

import com.marlonklc.model.Sale;
import com.marlonklc.model.Salesman;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class SaleParserTest {

    private String validData;
    private String invalidData;

    @Before
    public void startUp() {
        validData = "10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        invalidData = "10ç[1-10-100,2-30-2.50,3-40-3.10]";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid() {
        SaleParser.of().parse(invalidData);
    }

    @Test
    public void testTotalSale() {
        Sale sale = SaleParser.of().parse(validData);

        assertEquals(new BigDecimal("1199.00"), sale.getTotal());
    }

    @Test
    public void testValid() {
        Sale sale = SaleParser.of().parse(validData);

        assertEquals(Long.valueOf(10), sale.getId());
        assertEquals(3, sale.getSalesItems().size());
        assertEquals("Diego", sale.getSalesmanName());
    }
}
