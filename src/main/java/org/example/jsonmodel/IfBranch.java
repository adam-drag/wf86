package org.example.jsonmodel;

public class IfBranch {

    private IfStatement statement;
    private Object returnValue;

    public IfBranch() {
    }

    public IfStatement getStatement() {
        return statement;
    }

    public void setStatement(IfStatement statement) {
        this.statement = statement;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public boolean hasNestedIfToProcess() {
        return this.statement != null;
    }

    public boolean hasReturnValue() {
        return this.returnValue != null;
    }
    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
