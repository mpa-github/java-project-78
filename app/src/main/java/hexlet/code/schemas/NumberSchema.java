package hexlet.code.schemas;

import hexlet.code.restrictions.EnumRestriction;
import hexlet.code.restrictions.Restriction;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Restriction<Object> restriction = (object) -> object instanceof Number;
        addRestriction(EnumRestriction.IS_INSTANCE, restriction);
    }

    public NumberSchema required() {
        setRequired();
        return this;
    }

    public NumberSchema positive() {
        Restriction<Object> restriction = (object) -> (Integer) object > 0;
        addRestriction(EnumRestriction.POSITIVE, restriction);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        Restriction<Object> restriction = (object) -> ((Integer) object >= minValue && (Integer) object <= maxValue);
        addRestriction(EnumRestriction.RANGE, restriction);
        return this;
    }
}
