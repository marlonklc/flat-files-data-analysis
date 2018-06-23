package com.marlonklc.parser;

import java.util.StringTokenizer;

public interface Parser<T> {

    T parse(String data);

    default StringTokenizer split(String str) {
        return split(str, "ç");
    }

    default StringTokenizer split(String str, String delim) {
        return new StringTokenizer(str, delim);
    }
}
