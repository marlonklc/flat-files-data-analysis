package com.marlonklc.service;

import com.marlonklc.factory.ParserFactory;
import com.marlonklc.model.Customer;
import com.marlonklc.model.DataAnalysis;
import com.marlonklc.model.Sale;
import com.marlonklc.model.Salesman;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ExtractDataAnalysisService {

    public DataAnalysis processFile(Path path) throws IOException {
        Map<Class, List<Object>> modelsMap = Files.lines(path)
                .filter(linesHasText())
                .map(this::parseLine)
                .collect(Collectors.groupingBy(Object::getClass));

        List<Customer> customers = (List) modelsMap.get(Customer.class);
        List<Salesman> salesman = (List) modelsMap.get(Salesman.class);
        List<Sale> sales = (List) modelsMap.get(Sale.class);

        return DataAnalysis.of(customers, salesman, sales);
    }

    public Predicate<String> linesHasText() {
        return line -> StringUtils.hasText(line);
    }

    public Object parseLine(String line) {
        String type = line.substring(0, 3);
        String data = line.substring(4);
        return ParserFactory.of(type).parse(data);
    }
}
