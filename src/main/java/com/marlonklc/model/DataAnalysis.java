package com.marlonklc.model;

import java.util.List;

public class DataAnalysis {

    private List<Customer> customers;
    private List<Salesman> salesman;
    private List<Sale> sales;

    private DataAnalysis(List<Customer> customers, List<Salesman> salesman, List<Sale> sales) {
        this.customers = customers;
        this.salesman = salesman;
        this.sales = sales;
    }

    public static DataAnalysis of(List<Customer> customers, List<Salesman> salesman, List<Sale> sales) {
        return new DataAnalysis(customers, salesman, sales);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Salesman> getSalesman() {
        return salesman;
    }

    public List<Sale> getSales() {
        return sales;
    }
}
