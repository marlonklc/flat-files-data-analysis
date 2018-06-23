package com.marlonklc.summary;

import com.marlonklc.model.DataAnalysis;
import com.marlonklc.model.Sale;

import java.util.Map;
import java.util.stream.Collectors;

public class WorstSalesmanSummary implements Summary {

    @Override
    public String generate(DataAnalysis dataAnalysis) {
        String worstSalesman = getWorstSalesman(dataAnalysis);
        return "Worst salesman: " + worstSalesman + "\n";
    }

    private String getWorstSalesman(DataAnalysis dataAnalysis) {
        Map<String, Double> collect = dataAnalysis.getSales().stream()
                .map(model -> (Sale) model)
                .collect(Collectors.groupingBy(Sale::getSalesmanName, Collectors.summingDouble(sale -> sale.getTotal().doubleValue())));

        String name = collect.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return name;
    }
}
