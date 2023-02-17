package hexlet.code.schema;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {

    private static NumberSchema schema;

    @BeforeAll
    static void init() {
        Validator validator = new Validator();
        schema = validator.number();
    }

    @Test
    void testNumberSchema() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid("10"));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));

        schema.clear();

        assertTrue(schema.isValid(-1));
        assertTrue(schema.isValid(null));
        assertFalse(schema.positive().isValid(-1));
        assertTrue(schema.isValid(null));
        assertFalse(schema.required().isValid("1"));
    }
}