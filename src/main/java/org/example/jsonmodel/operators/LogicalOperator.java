package org.example.jsonmodel.operators;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LogicalOperator {
    AND("&&"), OR("||");

    private final String symbol;

    LogicalOperator(String symbol) {
        this.symbol = symbol;
    }

    @JsonValue
    public String getSymbol() {
        return symbol;
    }
}