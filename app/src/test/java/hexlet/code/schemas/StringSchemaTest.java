package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {

    private static final String EMPTY_STRING = "";
    private static final String SOME_STRING = "Some string";
    private static final String LENGTH_8_STRING = "Length 8";
    private static final String LENGTH = "Length";
    private static final String CONTAINS_WORD_STRING = "Contains word";
    private static final String WORD = "word";
    private static StringSchema schema;

    @BeforeAll
    static void init() {
        Validator validator = new Validator();
        schema = validator.string();
    }

    @Test
    void testStringSchema() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(EMPTY_STRING));
        assertTrue(schema.isValid(SOME_STRING));
        assertTrue(schema.isValid(LENGTH_8_STRING));
        assertTrue(schema.isValid(CONTAINS_WORD_STRING));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(EMPTY_STRING));
        assertTrue(schema.isValid(SOME_STRING));

        assertTrue(schema.contains(WORD).isValid(CONTAINS_WORD_STRING));
        assertFalse(schema.contains(WORD).isValid(SOME_STRING));

        assertFalse(schema.minLength(8).isValid(LENGTH_8_STRING));
        assertTrue(schema.contains(LENGTH).minLength(8).isValid(LENGTH_8_STRING));
        assertTrue(schema.minLength(8).isValid(LENGTH_8_STRING));
        assertFalse(schema.contains(WORD).isValid(WORD));

        schema.clear();

        assertTrue(schema.contains(LENGTH).isValid(null));
        assertFalse(schema.contains(LENGTH).isValid(SOME_STRING));
        assertFalse(schema.required().contains(LENGTH).isValid(null));
    }
}
