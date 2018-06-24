package com.marlonklc.parser;

import com.marlonklc.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerParserTest {

    private String validData;
    private String invalidData;

    @Before
    public void startUp() {
        validData = "1234567890123456çJoseDaSilvaçRural";
        invalidData = "1234567890123456çJoseDaSilva";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid() {
        CustomerParser.of().parse(invalidData);
    }

    @Test
    public void testValid() {
        Customer customer = CustomerParser.of().parse(validData);

        assertEquals("1234567890123456", customer.getCnpj().getValue());
        assertEquals("JoseDaSilva", customer.getName());
        assertEquals("Rural", customer.getBusinessArea().getValue());
    }
}
