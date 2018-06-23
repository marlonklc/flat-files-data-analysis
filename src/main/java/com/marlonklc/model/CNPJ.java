package com.marlonklc.model;

import org.springframework.util.Assert;

import java.util.Objects;

public class CNPJ {

    private final String cnpj;

    private CNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public static CNPJ of(String cnpj) {
        Assert.hasText(cnpj, "cnpj must be defined!");

        return new CNPJ(cnpj);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CNPJ cnpj1 = (CNPJ) o;
        return Objects.equals(cnpj, cnpj1.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return "CNPJ{" +
                "cnpj='" + cnpj + '\'' +
                '}';
    }
}
