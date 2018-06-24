package com.marlonklc.model;

import org.springframework.util.Assert;

import java.util.Objects;

public class BusinessArea {

    private final String value;

    private BusinessArea(String value) {
        this.value = value;
    }

    public static BusinessArea of(String value) {
        Assert.hasText(value, "business area must be defined!");

        return new BusinessArea(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessArea that = (BusinessArea) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BusinessArea{" +
                "value='" + value + '\'' +
                '}';
    }
}
