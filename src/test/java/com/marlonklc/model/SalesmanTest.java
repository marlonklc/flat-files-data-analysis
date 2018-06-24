package com.marlonklc.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SalesmanTest {

    private String stringCpf1;
    private String stringCpf2;

    @Before
    public void startUp() {
        stringCpf1 = "1234567890123";
        stringCpf2 = "1230123456789";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullArguments() {
        Salesman.of(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArguments() {
        Salesman.of(Cpf.of(stringCpf1), "", BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSalaryEqualsZero() {
        Salesman.of(Cpf.of(stringCpf1), "salesman test", BigDecimal.ZERO);
    }

    @Test
    public void testValidArguments() {
        Salesman salesman = Salesman.of(Cpf.of(stringCpf1), "salesman test", BigDecimal.TEN);

        assertEquals(stringCpf1, salesman.getCpf().getValue());
        assertEquals("salesman test", salesman.getName());
        assertEquals(BigDecimal.TEN, salesman.getSalary());
    }

    @Test
    public void testEquals() {
        Salesman salesman1 = Salesman.of(Cpf.of(stringCpf1), "salesman test", BigDecimal.ONE);
        Salesman salesman2 = Salesman.of(Cpf.of(stringCpf1), "salesman test", BigDecimal.ONE);

        assertEquals(salesman1, salesman2);
    }

    @Test
    public void testNotEquals() {
        Salesman salesman1 = Salesman.of(Cpf.of(stringCpf1), "salesman test", BigDecimal.ONE);
        Salesman salesman2 = Salesman.of(Cpf.of(stringCpf2), "salesman test", BigDecimal.ONE);

        assertNotEquals(salesman1, salesman2);
    }

}
