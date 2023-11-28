package org.example.jsonmodel;

import org.example.jsonmodel.operators.LogicalOperator;

import java.util.List;

public class LogicalCondition implements Condition {
    private LogicalOperator operator;
    private List<ComparisonCondition> conditions;

    public LogicalCondition() {
    }

    public LogicalOperator getOperator() {
        return operator;
    }

    public void setOperator(LogicalOperator operator) {
        this.operator = operator;
    }

    public List<ComparisonCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<ComparisonCondition> conditions) {
        this.conditions = conditions;
    }

}
