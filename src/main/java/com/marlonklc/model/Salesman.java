package com.marlonklc.model;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;

public class Salesman {

    private final Cpf cpf;
    private final String name;
    private final BigDecimal salary;

    private Salesman(Cpf cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public static Salesman of(Cpf cpf, String name, BigDecimal salary) {
        Assert.notNull(cpf, "Salesman 'cpf' cannot be null!");
        Assert.hasText(name, "Salesman 'name' cannot be null!");
        Assert.notNull(salary, "Salesman 'salary' cannot be null!");

        return new Salesman(cpf, name, salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(cpf, salesman.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "cpf=" + cpf +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
