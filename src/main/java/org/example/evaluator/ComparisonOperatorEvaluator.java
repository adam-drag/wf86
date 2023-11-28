package org.example.evaluator;

import org.example.jsonmodel.operators.ComparisonOperator;

public class ComparisonOperatorEvaluator {
    public static boolean evaluateOperator(Object input, Object variable, ComparisonOperator operator) {
        int result = compare(input, variable);

        return switch (operator) {
            case GREATER_THAN -> result > 0;
            case GREATER_THAN_OR_EQUAL -> result >= 0;
            case LESS_THAN -> result < 0;
            case LESS_THAN_OR_EQUAL -> result <= 0;
            case EQUAL -> result == 0;
            case NOT_EQUAL -> result != 0;
        };
    }

    private static int compare(Object input, Object variable) {
        return switch (input) {
            case Double v when variable instanceof Double -> Double.compare(v, (Double) variable);
            case String s when variable instanceof String -> s.compareTo((String) variable);
            case Integer i when variable instanceof Integer -> Integer.compare(i, (Integer) variable);
            case null, default -> {
                assert input != null;
                throw new IllegalArgumentException("Unsupported types for comparison: " +
                        input.getClass().getSimpleName() + " and " + variable.getClass().getSimpleName());
            }
        };
    }
}
