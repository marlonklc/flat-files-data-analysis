package com.marlonklc.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class CpfTest {

    private String stringCpf1;
    private String stringCpf2;

    @Before
    public void startUp() {
        stringCpf1 = "1234567890123";
        stringCpf2 = "1230123456789";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        Cpf.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        Cpf.of(" ");
    }

    @Test
    public void testValid() {
        Cpf cpf = Cpf.of(stringCpf1);

        assertEquals(stringCpf1, cpf.getValue());
    }

    @Test
    public void testEquals() {
        Cpf cpf1 = Cpf.of(stringCpf1);
        Cpf cpf2 = Cpf.of(stringCpf1);

        assertEquals(cpf1, cpf2);
        assertEquals(cpf1.getValue(), cpf2.getValue());
    }

    @Test
    public void testNotEquals() {
        Cpf cpf1 = Cpf.of(stringCpf1);
        Cpf cpf2 = Cpf.of(stringCpf2);

        assertNotEquals(cpf1, cpf2);
        assertNotEquals(cpf1.getValue(), cpf2.getValue());
    }

}
