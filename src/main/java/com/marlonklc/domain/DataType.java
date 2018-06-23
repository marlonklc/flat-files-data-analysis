package com.marlonklc.domain;

public enum DataType {

    SALESMAN("001"),
    CUSTOMER("002"),
    SALES("003"),
    ;

    protected final String value;

    DataType(String value) {
        this.value = value;
    }

    public boolean isEquals(String line) {
        return value.equals(line);
    }

}
