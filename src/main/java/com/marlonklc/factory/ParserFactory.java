package com.marlonklc.factory;

import com.marlonklc.domain.DataType;
import com.marlonklc.parser.CustomerParser;
import com.marlonklc.parser.Parser;
import com.marlonklc.parser.SaleParser;
import com.marlonklc.parser.SalesmanParser;

public class ParserFactory {

    public static Parser of(String type) {
        if (DataType.SALESMAN.isEquals(type)) {
            return SalesmanParser.of();
        } else if (DataType.CUSTOMER.isEquals(type)) {
            return CustomerParser.of();
        } else if( DataType.SALES.isEquals(type)) {
            return SaleParser.of();
        }

        throw new IllegalArgumentException("Line data type '" + type + "' is unknown!");
    }
}
