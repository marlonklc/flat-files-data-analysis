package com.marlonklc.model;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Sale {

    private final Long id;
    private final List<SaleItem> salesItems;
    private final String salesmanName;

    private Sale(long id, List<SaleItem> salesItems, String salesmanName) {
        this.id = id;
        this.salesItems = salesItems;
        this.salesmanName = salesmanName;
    }

    public static Sale of(Long id, List<SaleItem> salesItems, String salesmanName) {
        Assert.notNull(id, "Sale 'id' cannot be null!");
        Assert.notEmpty(salesItems, "Sale 'saleItems' cannot be null!");
        Assert.hasText(salesmanName, "Sale 'salesmanName' cannot be null!");

        return new Sale(id, salesItems, salesmanName);
    }

    public Long getId() {
        return id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public BigDecimal getTotal() {
        return salesItems.stream()
                .map(SaleItem::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", salesItems=" + salesItems +
                ", salesmanName='" + salesmanName + '\'' +
                '}';
    }
}
