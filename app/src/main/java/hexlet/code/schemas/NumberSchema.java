package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        Restriction<Object> restriction = (number) -> number instanceof Number
                && (Integer) number > 0;
        this.restrictions.put(EnumRestriction.POSITIVE, restriction);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        Restriction<Object> restriction = (number) -> number instanceof Number
                && ((Integer) number >= minValue && (Integer) number <= maxValue);
        this.restrictions.put(EnumRestriction.RANGE, restriction);
        return this;
    }
}
