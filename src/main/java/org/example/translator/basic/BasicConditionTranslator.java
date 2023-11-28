package org.example.translator.basic;

import org.example.jsonmodel.ComparisonCondition;
import org.example.jsonmodel.Condition;
import org.example.jsonmodel.LogicalCondition;
import org.example.translator.ConditionTranslator;

public class BasicConditionTranslator implements ConditionTranslator {

    @Override
    public String translate(Condition condition) {
        if (condition instanceof ComparisonCondition) {
            return this.translateComparisonCondition((ComparisonCondition) condition);
        } else if (condition instanceof LogicalCondition) {
            return this.translateLogicalCondition((LogicalCondition) condition);
        }
        throw new RuntimeException("Unsupported condition");
    }

    private String translateComparisonCondition(ComparisonCondition comparisonCondition) {
        return comparisonCondition.getInput() + " "
                + comparisonCondition.getOperator().getSymbol()
                + " " + comparisonCondition.getVariable().toString();
    }

    private String translateLogicalCondition(LogicalCondition logicalCondition) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < logicalCondition.getConditions().size(); i++) {
            sb.append(this.translateComparisonCondition(logicalCondition.getConditions().get(i)));
            if (i != logicalCondition.getConditions().size() - 1) {
                sb.append(" ").append(logicalCondition.getOperator().getSymbol()).append(" ");
            }
        }
        return sb.toString();
    }
}
