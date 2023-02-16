package hexlet.code;

import hexlet.code.schema.number.NumberSchema;
import hexlet.code.schema.string.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema(String.class);
    }

    public NumberSchema number() {
        return new NumberSchema(Integer.class);
    }
}
