package com.marlonklc.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CnpjTest {

    private String stringCnpj1;
    private String stringCnpj2;

    @Before
    public void startUp() {
        stringCnpj1 = "1234567890123456";
        stringCnpj2 = "1234560123456789";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        Cnpj.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        Cnpj.of(" ");
    }

    @Test
    public void testValid() {
        Cnpj cnpj = Cnpj.of(stringCnpj1);

        assertEquals(stringCnpj1, cnpj.getValue());
    }

    @Test
    public void testEquals() {
        Cnpj cnpj1 = Cnpj.of(stringCnpj1);
        Cnpj cnpj2 = Cnpj.of(stringCnpj1);

        assertEquals(cnpj1, cnpj2);
        assertEquals(cnpj1.getValue(), cnpj2.getValue());
    }

    @Test
    public void testNotEquals() {
        Cnpj cnpj1 = Cnpj.of(stringCnpj1);
        Cnpj cnpj2 = Cnpj.of(stringCnpj2);

        assertNotEquals(cnpj1, cnpj2);
        assertNotEquals(cnpj1.getValue(), cnpj2.getValue());
    }

}
