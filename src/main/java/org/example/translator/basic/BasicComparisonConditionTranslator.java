package org.example.translator.basic;

import org.example.jsonmodel.ComparisonCondition;
import org.example.translator.ComparisonConditionTranslator;

public class BasicComparisonConditionTranslator implements ComparisonConditionTranslator {
    @Override
    public String translate(ComparisonCondition comparisonCondition) {
        return comparisonCondition.getInput() + " "
                + comparisonCondition.getOperator().getSymbol()
                + " " + comparisonCondition.getVariable().toString();
    }
}
