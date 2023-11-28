package org.example.translator;

import org.example.jsonmodel.Condition;
import org.example.jsonmodel.IfBranch;
import org.example.jsonmodel.IfStatement;

public abstract class Translator {

    protected abstract String translateIfHeader(IfStatement ifStatement);

    protected abstract String translateCondition(Condition condition);

    protected abstract String translateBranchReturnValue(IfBranch branch);

    protected abstract String translateElseBranch(IfBranch branch);

    public String translate(IfStatement ifStatement) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.translateIfHeader(ifStatement));

        if (ifStatement.getTrueBranch().hasNestedIfToProcess()) {
            String netedIfStatementString = this.translate(ifStatement.getTrueBranch().getStatement());
            sb.append(netedIfStatementString);
        } else {
            sb.append(this.translateBranchReturnValue(ifStatement.getTrueBranch()));
        }

        if (ifStatement.hasElseBranch()) {
            sb.append(this.translateElseBranch(ifStatement.getFalseBranch()));
        }

        return sb.toString();
    }
}
