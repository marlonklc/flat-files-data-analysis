package com.marlonklc.summary;

import com.marlonklc.model.DataAnalysis;
import com.marlonklc.model.Sale;

import java.util.Comparator;

public class MostExpensiveSaleSummary implements Summary {

    @Override
    public String generate(DataAnalysis dataAnalysis) {
        Sale mostExpansiveSale = getMostExpansiveSale(dataAnalysis);
        return "ID of the most expensive sale: " + mostExpansiveSale.getId() + "\n";
    }

    private Sale getMostExpansiveSale(DataAnalysis dataAnalysis) {
        return dataAnalysis.getSales().stream()
                .map(o -> (Sale) o)
                .max(Comparator.comparing(Sale::getTotal))
                .get();
    }
}
