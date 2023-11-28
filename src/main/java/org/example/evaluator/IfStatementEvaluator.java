package org.example.evaluator;

import org.example.jsonmodel.*;
import org.example.jsonmodel.operators.LogicalOperator;

import java.util.Map;

public class IfStatementEvaluator {

    public Object evaluate(Payload payload) {
        Map<String, Object> inputs = payload.getInput();
        IfStatement statement = payload.getStatement();
        return this.processIfStatement(statement, inputs);
    }

    private Object processIfStatement(IfStatement statement, Map<String, Object> inputs) {
        boolean isConditionTrue = this.processCondition(statement.getCondition(), inputs);
        if (isConditionTrue) {
            return processBranch(statement.getTrueBranch(), inputs);
        } else {
            return processBranch(statement.getFalseBranch(), inputs);
        }
    }

    private boolean processCondition(Condition condition, Map<String, Object> inputs) {
        if (isComparisonCondition(condition)) {
            return this.processComparisonCondition((ComparisonCondition) condition, inputs);
        } else if (isLogicalCondition(condition)) {
            return this.processLogicalCondition((LogicalCondition) condition, inputs);
        }
        throw new RuntimeException("Unsupported condition type");
    }

    private Object processBranch(IfBranch ifBranch, Map<String, Object> inputs) {
        if (ifBranch.hasNestedIfToProcess()) {
            return this.processIfStatement(ifBranch.getStatement(), inputs);
        } else if (ifBranch.hasReturnValue()) {
            return ifBranch.getReturnValue();
        } else {
            throw new RuntimeException("Malformed branch");
        }
    }

    private boolean processComparisonCondition(ComparisonCondition condition, Map<String, Object> inputs) {
        Object inputValue = inputs.get(condition.getInput());
        return ComparisonOperatorEvaluator.evaluateOperator(inputValue, condition.getVariable(), condition.getOperator());
    }

    private boolean processLogicalCondition(LogicalCondition logicalCondition, Map<String, Object> inputs) {
        if (isAnd(logicalCondition)) {
            return processLogicalAnd(logicalCondition, inputs);
        } else if (isOr(logicalCondition)) {
            return processLogicalOr(logicalCondition, inputs);
        } else {
            throw new RuntimeException("Unsupported operator");
        }
    }

    private static boolean isLogicalCondition(Condition condition) {
        return condition instanceof LogicalCondition;
    }

    private static boolean isComparisonCondition(Condition condition) {
        return condition instanceof ComparisonCondition;
    }

    private static boolean isOr(LogicalCondition logicalCondition) {
        return LogicalOperator.OR == logicalCondition.getOperator();
    }

    private static boolean isAnd(LogicalCondition logicalCondition) {
        return LogicalOperator.AND == logicalCondition.getOperator();
    }

    private boolean processLogicalOr(LogicalCondition logicalCondition, Map<String, Object> inputs) {
        for (ComparisonCondition condition : logicalCondition.getConditions()) {
            boolean comparisonResult = this.processComparisonCondition(condition, inputs);
            if (comparisonResult) {
                return true;
            }
        }
        return false;
    }

    private boolean processLogicalAnd(LogicalCondition logicalCondition, Map<String, Object> inputs) {
        for (ComparisonCondition condition : logicalCondition.getConditions()) {
            boolean comparisonResult = this.processComparisonCondition(condition, inputs);
            if (!comparisonResult) {
                return false;
            }
        }
        return true;
    }
}
