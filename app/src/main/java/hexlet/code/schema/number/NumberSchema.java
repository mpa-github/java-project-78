package hexlet.code.schema.number;

import hexlet.code.schema.BaseSchema;
import hexlet.code.schema.Restriction;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer, NumberRestriction> {

    public NumberSchema(Class<Integer> thisClass) {
        super(thisClass);
    }

    public NumberSchema required() {
        Restriction<Integer> restriction = Objects::nonNull;
        restrictions.put(NumberRestriction.REQUIRED, restriction);
        return this;
    }

    public NumberSchema positive() {
        Restriction<Integer> restriction = (number) -> number == null || number > 0;
        restrictions.put(NumberRestriction.POSITIVE, restriction);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        Restriction<Integer> restriction = (number) -> number == null || (number >= minValue && number <= maxValue);
        restrictions.put(NumberRestriction.RANGE, restriction);
        return this;
    }
}
