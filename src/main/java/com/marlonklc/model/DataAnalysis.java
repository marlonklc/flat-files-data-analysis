package com.marlonklc.model;

import java.util.List;

public class DataAnalysis {

    List<Object> customers;
    List<Object> salesman;
    List<Object> sales;

    private DataAnalysis(List<Object> customers, List<Object> salesman, List<Object> sales) {
        this.customers = customers;
        this.salesman = salesman;
        this.sales = sales;
    }

    public static DataAnalysis of(List<Object> customers, List<Object> salesman, List<Object> sales) {
        return new DataAnalysis(customers, salesman, sales);
    }

    public List<Object> getCustomers() {
        return customers;
    }

    public List<Object> getSalesman() {
        return salesman;
    }

    public List<Object> getSales() {
        return sales;
    }
}
