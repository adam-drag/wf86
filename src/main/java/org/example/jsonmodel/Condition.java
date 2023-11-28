package org.example.jsonmodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComparisonCondition.class, name = "comparison"),
        @JsonSubTypes.Type(value = LogicalCondition.class, name = "logical")
})
public interface Condition {
}
