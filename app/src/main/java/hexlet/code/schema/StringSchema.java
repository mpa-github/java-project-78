package hexlet.code.schema;

public final class StringSchema extends BaseSchema {

    private static final String EMPTY_STRING = "";

    public StringSchema required() {
        this.isRequired = true;
        Restriction<Object> restriction = (string) -> !string.equals(EMPTY_STRING);
        this.restrictions.put(EnumRestriction.NOT_EMPTY, restriction);
        return this;
    }

    public StringSchema contains(String charSequence) {
        Restriction<Object> restriction = (string) -> string instanceof String
                && ((String) string).contains(charSequence);
        this.restrictions.put(EnumRestriction.CONTAINS, restriction);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Restriction<Object> restriction = (string) -> string instanceof String
                && ((String) string).length() >= minLength;
        this.restrictions.put(EnumRestriction.MIN_LENGTH, restriction);
        return this;
    }
}
