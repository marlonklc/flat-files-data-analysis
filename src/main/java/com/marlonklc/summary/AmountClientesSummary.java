package com.marlonklc.summary;

import com.marlonklc.model.DataAnalysis;

public class AmountClientesSummary implements Summary {

    @Override
    public String generate(DataAnalysis dataAnalysis) {
        return "Amount of clients: " + dataAnalysis.getCustomers().size() + "\n";
    }
}
