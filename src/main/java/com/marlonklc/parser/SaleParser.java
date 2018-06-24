package com.marlonklc.parser;

import com.marlonklc.model.Sale;
import com.marlonklc.model.SaleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SaleParser implements Parser<Sale> {

    public static SaleParser of() {
        return new SaleParser();
    }

    @Override
    public Sale parse(String data) {
        StringTokenizer tokenizer =  split(data);

        if(tokenizer.countTokens() < 3) throw new IllegalArgumentException("SaleParser: data has less parameters!");

        Long id = Long.valueOf(tokenizer.nextToken());
        List<SaleItem> saleItems = parseSaleItems(tokenizer.nextToken());
        String salesmanName = tokenizer.nextToken();

        return Sale.of(id, saleItems, salesmanName);
    }

    private List<SaleItem> parseSaleItems(String line) {
        String data = line.replaceAll("[\\[|\\]]", "");

        StringTokenizer tokenizer = split(data, ",");

        Parser<SaleItem> parser = SaleItemParser.of();

        List<SaleItem> saleItems = new ArrayList<>();

        while(tokenizer.hasMoreTokens()) {
            saleItems.add(parseSaleItem(parser, tokenizer.nextToken()));
        }

        return saleItems;
    }

    private SaleItem parseSaleItem(Parser<SaleItem> parser, String line) {
        return parser.parse(line);
    }
}
