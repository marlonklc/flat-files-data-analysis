package com.marlonklc.model;

import org.springframework.util.Assert;

import java.util.Objects;

public class Cpf {

    private final String cpf;

    private Cpf(String cpf) {
        this.cpf = cpf;
    }

    public static Cpf of(String cpf) {
        Assert.hasText(cpf, "cpf must be defined!");

        return new Cpf(cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf1 = (Cpf) o;
        return Objects.equals(cpf, cpf1.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Cpf{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
