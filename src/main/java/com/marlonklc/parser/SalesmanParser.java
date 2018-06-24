package com.marlonklc.parser;

import com.marlonklc.model.Cpf;
import com.marlonklc.model.Salesman;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class SalesmanParser implements Parser<Salesman> {

    public static SalesmanParser of() {
        return new SalesmanParser();
    }

    @Override
    public Salesman parse(String data) {
        StringTokenizer tokenizer = split(data);

        if(tokenizer.countTokens() < 3) throw new IllegalArgumentException("SalesmanParser: data has less parameters!");

        Cpf cpf = Cpf.of(tokenizer.nextToken());
        String name = tokenizer.nextToken();
        BigDecimal salary = new BigDecimal(tokenizer.nextToken());

        return Salesman.of(cpf, name, salary);
    }
}
