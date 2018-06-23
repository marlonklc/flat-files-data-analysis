package com.marlonklc.model;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;

public class SaleItem {

    private final Long id;
    private final Integer quantity;
    private final BigDecimal price;

    private SaleItem(Long id, Integer quantity, BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public static SaleItem of(Long id, Integer quantity, BigDecimal price) {
        Assert.notNull(id, "SaleItem 'id' cannot be null!");
        Assert.notNull(quantity, "SaleItem 'quantity' cannot be null!");
        Assert.notNull(price, "SaleItem 'id' cannot be null!");

        if (quantity <= 0) throw new IllegalArgumentException("SaleItem 'quantity' must be higher than zero!");
        if (price.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("SaleItem 'price' must be higher than zero!");

        return new SaleItem(id, quantity, price);
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(id, saleItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
