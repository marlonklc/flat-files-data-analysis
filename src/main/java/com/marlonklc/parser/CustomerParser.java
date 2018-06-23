package com.marlonklc.parser;

import com.marlonklc.model.BusinessArea;
import com.marlonklc.model.CNPJ;
import com.marlonklc.model.Customer;

import java.util.StringTokenizer;

public class CustomerParser implements Parser<Customer> {

    public static CustomerParser of() {
        return new CustomerParser();
    }

    @Override
    public Customer parse(String data) {
        StringTokenizer tokenizer = split(data);

        CNPJ cnpj = CNPJ.of(tokenizer.nextToken());
        String name = tokenizer.nextToken();
        BusinessArea businessArea = BusinessArea.of(tokenizer.nextToken());

        return Customer.of(cnpj, name, businessArea);
    }
}
