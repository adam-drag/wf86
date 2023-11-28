package org.example.translator;

import org.example.jsonmodel.LogicalCondition;

public interface LogicalConditionTranslator {

    String translate(LogicalCondition logicalCondition);
}
