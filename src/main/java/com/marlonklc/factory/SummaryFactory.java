package com.marlonklc.factory;

import com.marlonklc.model.DataAnalysis;
import com.marlonklc.summary.AmountClientesSummary;
import com.marlonklc.summary.AmountSalesmanSummary;
import com.marlonklc.summary.MostExpensiveSaleSummary;
import com.marlonklc.summary.Summary;
import com.marlonklc.summary.WorstSalesmanSummary;

import java.util.ArrayList;
import java.util.List;

public class SummaryFactory {

    private List<Summary> summaries = new ArrayList<>();

    private DataAnalysis dataAnalysis;

    private SummaryFactory(DataAnalysis dataAnalysis) {
        this.dataAnalysis = dataAnalysis;
    }

    public static SummaryFactory ofDefault(DataAnalysis dataAnalysis) {
        SummaryFactory summaryFactory = new SummaryFactory(dataAnalysis);
        summaryFactory.addSummary(new AmountClientesSummary());
        summaryFactory.addSummary(new AmountSalesmanSummary());
        summaryFactory.addSummary(new MostExpensiveSaleSummary());
        summaryFactory.addSummary(new WorstSalesmanSummary());

        return summaryFactory;
    }

    public static SummaryFactory ofCustom(List<Summary> summaries, DataAnalysis dataAnalysis) {
        SummaryFactory summaryFactory = new SummaryFactory(dataAnalysis);

        summaries.forEach(summaryFactory::addSummary);

        return summaryFactory;
    }

    private void addSummary(Summary summary) {
        summaries.add(summary);
    }

    public byte[] getSummary() {
        StringBuilder sb = new StringBuilder();

        summaries.forEach(summary -> sb.append(summary.generate(dataAnalysis)));

        return sb.toString().getBytes();
    }
}
