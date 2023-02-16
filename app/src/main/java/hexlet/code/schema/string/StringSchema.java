package hexlet.code.schema.string;

import hexlet.code.schema.BaseSchema;
import hexlet.code.schema.Restriction;

public final class StringSchema extends BaseSchema<String, StringRestriction> {

    private static final String EMPTY_STRING = "";

    public StringSchema(Class<String> thisClass) {
        super(thisClass);
    }

    public StringSchema required() {
        Restriction<String> restriction = (string) -> string != null && !string.equals(EMPTY_STRING);
        restrictions.put(StringRestriction.REQUIRED, restriction);
        return this;
    }

    public StringSchema contains(String charSequence) {
        Restriction<String> restriction = (string) -> string == null || string.contains(charSequence);
        restrictions.put(StringRestriction.CONTAINS, restriction);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Restriction<String> restriction = (string) -> string == null || string.length() >= minLength;
        restrictions.put(StringRestriction.MIN_LENGTH, restriction);
        return this;
    }
}
