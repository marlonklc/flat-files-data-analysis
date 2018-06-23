package com.marlonklc.model;

import org.springframework.util.Assert;

import java.util.Objects;

public class CPF {

    private final String cpf;

    private CPF(String cpf) {
        this.cpf = cpf;
    }

    public static CPF of(String cpf) {
        Assert.hasText(cpf, "cpf must be defined!");

        return new CPF(cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf1 = (CPF) o;
        return Objects.equals(cpf, cpf1.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "CPF{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
