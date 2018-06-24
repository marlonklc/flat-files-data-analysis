package com.marlonklc.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTest {

    private String stringCnpj1;
    private String stringCnpj2;

    @Before
    public void startUp() {
        stringCnpj1 = "1234567890123456";
        stringCnpj2 = "1234560123456789";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullArguments() {
        Customer.of(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArguments() {
        Customer.of(Cnpj.of(stringCnpj1), "", BusinessArea.of("technology"));
    }

    @Test
    public void testValidArguments() {
        Customer customer = Customer.of(Cnpj.of(stringCnpj1), "customer test", BusinessArea.of("technology"));

        assertEquals(stringCnpj1, customer.getCnpj().getValue());
        assertEquals("customer test", customer.getName());
        assertEquals("technology", customer.getBusinessArea().getValue());
    }

    @Test
    public void testEquals() {
        Customer customer1 = Customer.of(Cnpj.of(stringCnpj1), "customer test 1", BusinessArea.of("technology"));
        Customer customer2 = Customer.of(Cnpj.of(stringCnpj1), "customer test 2", BusinessArea.of("infrastructure"));

        assertEquals(customer1, customer2);
    }

    @Test
    public void testNotEquals() {
        Customer customer1 = Customer.of(Cnpj.of(stringCnpj1), "customer test 1", BusinessArea.of("technology"));
        Customer customer2 = Customer.of(Cnpj.of(stringCnpj2), "customer test 2", BusinessArea.of("infrastructure"));

        assertNotEquals(customer1, customer2);
    }

}
