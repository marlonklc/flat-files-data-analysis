package com.marlonklc.service;

import com.marlonklc.domain.DataType;
import com.marlonklc.factory.ParserFactory;
import com.marlonklc.model.Customer;
import com.marlonklc.model.Sale;
import com.marlonklc.model.Salesman;
import com.marlonklc.parser.Parser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FileProcessService {

    public void processFile(Path path) throws IOException {
        Map<Class, List<Object>> modelsMap = Files.lines(path)
                .filter(linesHasText())
                .map(this::parseLine)
                .collect(Collectors.groupingBy(Object::getClass));

        List<Object> customers = modelsMap.get(Customer.class);
        List<Object> salesman = modelsMap.get(Salesman.class);
        List<Object> sales = modelsMap.get(Sale.class);
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
