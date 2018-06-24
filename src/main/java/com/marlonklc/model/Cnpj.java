package com.marlonklc.model;

import org.springframework.util.Assert;

import java.util.Objects;

public class Cnpj {

    private final String cnpj;

    private Cnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static Cnpj of(String cnpj) {
        Assert.hasText(cnpj, "cnpj must be defined!");

        return new Cnpj(cnpj);
    }

    public String getValue() {
        return cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cnpj cnpj1 = (Cnpj) o;
        return Objects.equals(cnpj, cnpj1.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return "Cnpj{" +
                "cnpj='" + cnpj + '\'' +
                '}';
    }
}
