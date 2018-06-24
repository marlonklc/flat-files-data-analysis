package com.marlonklc.parser;

import com.marlonklc.model.Salesman;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SalesmanParserTest {

    private String validData;
    private String invalidData;

    @Before
    public void startUp() {
        validData = "1234567890123çDiegoç50000";
        invalidData = "1234567890123çDiego";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid() {
        SalesmanParser.of().parse(invalidData);
    }

    @Test
    public void testValid() {
        Salesman salesman = SalesmanParser.of().parse(validData);

        assertEquals("1234567890123", salesman.getCpf().getValue());
        assertEquals("Diego", salesman.getName());
        assertEquals(new BigDecimal("50000"), salesman.getSalary());
    }
}
