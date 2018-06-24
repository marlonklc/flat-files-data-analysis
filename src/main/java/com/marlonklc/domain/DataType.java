package com.marlonklc.domain;

public enum DataType {

    SALESMAN("001"),
    CUSTOMER("002"),
    SALE("003"),
    ;

    private final String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isEquals(String type) {
        return value.equals(type);
    }

}
