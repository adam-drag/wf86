package org.example.jsonmodel;

import org.example.jsonmodel.operators.ComparisonOperator;

public class ComparisonCondition implements Condition {
    private String input;
    private ComparisonOperator operator;
    private Object variable;

    public ComparisonCondition() {
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ComparisonOperator getOperator() {
        return operator;
    }

    public void setOperator(ComparisonOperator operator) {
        this.operator = operator;
    }

    public Object getVariable() {
        return variable;
    }

    public void setVariable(Object variable) {
        this.variable = variable;
    }
}
