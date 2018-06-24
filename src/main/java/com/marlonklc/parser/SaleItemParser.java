package com.marlonklc.parser;

import com.marlonklc.model.SaleItem;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class SaleItemParser implements Parser<SaleItem> {

    public static SaleItemParser of() {
        return new SaleItemParser();
    }

    @Override
    public SaleItem parse(String data) {
        StringTokenizer tokenizer = split(data, "-");

        if(tokenizer.countTokens() < 3) throw new IllegalArgumentException("SaleItemParser: data has less parameters!");

        Long id = Long.valueOf(tokenizer.nextToken());
        Integer quantity = Integer.valueOf(tokenizer.nextToken());
        BigDecimal price = new BigDecimal(tokenizer.nextToken());

        return SaleItem.of(id, quantity, price);
    }
}
