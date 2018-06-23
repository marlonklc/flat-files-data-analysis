package com.marlonklc.model;

import org.springframework.util.Assert;

import java.util.Objects;

public class Customer {

    private final Cnpj cnpj;
    private final String name;
    private final BusinessArea businessArea;

    private Customer(Cnpj cnpj, String name, BusinessArea businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public static Customer of(Cnpj cnpj, String name, BusinessArea businessArea) {
        Assert.notNull(cnpj, "Customer 'cnpj' cannot be null!");
        Assert.hasText(name, "Customer 'name' cannot be null");
        Assert.notNull(businessArea, "Customer 'businessArea' cannot be null!");

        return new Customer(cnpj, name, businessArea);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cnpj, customer.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cnpj=" + cnpj +
                ", name='" + name + '\'' +
                ", businessArea=" + businessArea +
                '}';
    }
}
