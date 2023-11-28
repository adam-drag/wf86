package org.example.jsonmodel.operators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComparisonOperator {
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    EQUAL("=="),
    NOT_EQUAL("!=");

    private final String symbol;

    ComparisonOperator(String symbol) {
        this.symbol = symbol;
    }

    @JsonValue
    public String getSymbol() {
        return symbol;
    }

    @JsonCreator
    public static ComparisonOperator fromString(String symbol) {
        for (ComparisonOperator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unsupported operator: " + symbol);
    }
}
