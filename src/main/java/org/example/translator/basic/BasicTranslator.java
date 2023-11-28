package org.example.translator.basic;

import org.example.jsonmodel.*;
import org.example.translator.Translator;

public class BasicTranslator extends Translator {

    private final BasicConditionTranslator basicConditionTranslator;

    public BasicTranslator(BasicConditionTranslator basicConditionTranslator) {
        this.basicConditionTranslator = basicConditionTranslator;
    }

    @Override
    protected String translateIfHeader(IfStatement ifStatement) {
        return "if (" +
                translateCondition(ifStatement.getCondition()) +
                ")" +
                System.lineSeparator();
    }

    @Override
    protected String translateCondition(Condition condition) {
        return this.basicConditionTranslator.translate(condition);
    }

    @Override
    protected String translateBranchReturnValue(IfBranch branch) {
        return "{" +
                System.lineSeparator() +
                branch.getReturnValue().toString() +
                System.lineSeparator() +
                "}" + System.lineSeparator();
    }

    @Override
    protected String translateElseBranch(IfBranch branch) {
        String elseString = " else ";
        if (branch.hasNestedIfToProcess()) {
            return elseString + this.translate(branch.getStatement());
        } else if (branch.hasReturnValue()) {
            return elseString + this.translateBranchReturnValue(branch);
        }
        throw new RuntimeException("Maformed if statement");
    }
}
