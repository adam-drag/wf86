package org.example.translator;

import org.example.jsonmodel.ComparisonCondition;
import org.example.jsonmodel.Condition;
import org.example.jsonmodel.LogicalCondition;

public interface ConditionTranslator {
    String translate(Condition condition);
}
