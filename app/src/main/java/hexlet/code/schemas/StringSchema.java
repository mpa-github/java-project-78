package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    private static final String EMPTY_STRING = "";

    public StringSchema() {
        Restriction<Object> restriction = (object) -> object instanceof String;
        addRestriction(EnumRestriction.IS_INSTANCE, restriction);
    }

    public StringSchema required() {
        setRequired();
        Restriction<Object> restriction = (object) -> !object.equals(EMPTY_STRING);
        addRestriction(EnumRestriction.NOT_EMPTY, restriction);
        return this;
    }

    public StringSchema contains(String charSequence) {
        Restriction<Object> restriction = (object) -> ((String) object).contains(charSequence);
        addRestriction(EnumRestriction.CONTAINS, restriction);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Restriction<Object> restriction = (object) -> ((String) object).length() >= minLength;
        addRestriction(EnumRestriction.MIN_LENGTH, restriction);
        return this;
    }
}
