package org.example.jsonmodel;

public class IfStatement {
    private String type;
    private Condition condition;
    private IfBranch trueBranch;
    private IfBranch falseBranch;

    public IfStatement() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public IfBranch getTrueBranch() {
        return trueBranch;
    }

    public void setTrueBranch(IfBranch trueBranch) {
        this.trueBranch = trueBranch;
    }

    public IfBranch getFalseBranch() {
        return falseBranch;
    }

    public boolean hasElseBranch() {
        return this.falseBranch != null;
    }

    public void setFalseBranch(IfBranch falseBranch) {
        this.falseBranch = falseBranch;
    }

}
