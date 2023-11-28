package org.example.jsonmodel;


import java.util.Map;

public class Payload {
    private Map<String, Object> input;
    private IfStatement statement;

    public Payload() {
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public IfStatement getStatement() {
        return statement;
    }

    public void setStatement(IfStatement statement) {
        this.statement = statement;
    }
}
