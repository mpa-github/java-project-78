package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        setRequired();
        return this;
    }

    public NumberSchema positive() {
        Restriction<Object> restriction = (object) -> object instanceof Number
                && (Integer) object > 0;
        addRestriction(EnumRestriction.POSITIVE, restriction);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        Restriction<Object> restriction = (object) -> object instanceof Number
                && ((Integer) object >= minValue && (Integer) object <= maxValue);
        addRestriction(EnumRestriction.RANGE, restriction);
        return this;
    }
}
