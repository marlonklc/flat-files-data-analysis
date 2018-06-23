package com.marlonklc.parser;

import com.marlonklc.model.CPF;
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

        CPF cpf = CPF.of(tokenizer.nextToken());
        String name = tokenizer.nextToken();
        BigDecimal salary = new BigDecimal(tokenizer.nextToken());

        return Salesman.of(cpf, name, salary);
    }
}
