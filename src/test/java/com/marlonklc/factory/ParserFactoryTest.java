package com.marlonklc.factory;

import com.marlonklc.domain.DataType;
import static org.junit.Assert.*;

import com.marlonklc.model.Customer;
import com.marlonklc.model.Sale;
import com.marlonklc.model.Salesman;
import org.junit.Before;
import org.junit.Test;

public class ParserFactoryTest {

    private String salesmanData;
    private String customerData;
    private String saleData;

    @Before
    public void startUp() {
        salesmanData = "1234567891234çDiegoç50000";
        customerData = "1234567890123456çJoseDaSilvaçRural";
        saleData = "10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidType() {
        ParserFactory.of("123");
    }

    @Test
    public void testValidType() {
        Object salesman = ParserFactory.of(DataType.SALESMAN.getValue()).parse(salesmanData);
        Object customer = ParserFactory.of(DataType.CUSTOMER.getValue()).parse(customerData);
        Object sale = ParserFactory.of(DataType.SALE.getValue()).parse(saleData);

        assertNotNull(salesman);
        assertNotNull(customer);
        assertNotNull(sale);
        assertTrue(salesman.getClass().isAssignableFrom(Salesman.class));
        assertTrue(customer.getClass().isAssignableFrom(Customer.class));
        assertTrue(sale.getClass().isAssignableFrom(Sale.class));
    }
}
