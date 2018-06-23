package com.marlonklc.summary;

import com.marlonklc.model.DataAnalysis;

public class AmountSalesmanSummary implements Summary {

    @Override
    public String generate(DataAnalysis dataAnalysis) {
        return "Amount of salesman: " + dataAnalysis.getSalesman().size() + "\n";
    }
}
