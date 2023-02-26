package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidatorTest {

    private static final String EMPTY_STRING = "";
    private static final String SOME_STRING = "Some string";
    private static final String LENGTH_8_STRING = "Length 8";
    private static final String LENGTH = "Length";
    private static final String CONTAINS_WORD_STRING = "Contains word";
    private static final String WORD = "word";

    private static Validator validator;
    private static StringSchema stringSchema;
    private static NumberSchema numberSchema;
    private static MapSchema mapSchema;

    @BeforeAll
    static void init() {
        validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
        mapSchema = validator.map();
    }

    @Test
    void testStringSchema() {
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(EMPTY_STRING));
        assertTrue(stringSchema.isValid(SOME_STRING));
        assertTrue(stringSchema.isValid(LENGTH_8_STRING));
        assertTrue(stringSchema.isValid(CONTAINS_WORD_STRING));

        stringSchema.required();

        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(EMPTY_STRING));
        assertTrue(stringSchema.isValid(SOME_STRING));

        assertTrue(stringSchema.contains(WORD).isValid(CONTAINS_WORD_STRING));
        assertFalse(stringSchema.contains(WORD).isValid(SOME_STRING));

        assertFalse(stringSchema.minLength(8).isValid(LENGTH_8_STRING));
        assertTrue(stringSchema.contains(LENGTH).isValid(LENGTH_8_STRING));
        assertFalse(stringSchema.contains(WORD).isValid(WORD));

        stringSchema.clear();

        assertTrue(stringSchema.contains(LENGTH).isValid(null));
        assertFalse(stringSchema.contains(LENGTH).isValid(SOME_STRING));
        assertFalse(stringSchema.required().contains(LENGTH).isValid(null));
    }

    @Test
    void testNumberSchema() {
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.positive().isValid(null));

        numberSchema.required();

        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid("10"));
        assertFalse(numberSchema.isValid(-10));
        assertFalse(numberSchema.isValid(0));

        numberSchema.range(5, 10);

        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
        assertFalse(numberSchema.isValid(11));

        numberSchema.clear();

        assertTrue(numberSchema.isValid(-1));
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.positive().isValid(-1));
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.required().isValid("1"));
    }

    @Test
    void testMapSchema() {
        assertTrue(mapSchema.isValid(null));

        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data));

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));

        mapSchema.clear();

        assertTrue(mapSchema.isValid(null));
    }

    @Test
    void testMapSchemaWithShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(mapSchema.isValid(human4));

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Ivan");
        human5.put("age", "string");
        assertFalse(mapSchema.isValid(human5));

        Map<String, Object> human6 = new HashMap<>();
        human6.put("name", "Alex");
        assertFalse(mapSchema.isValid(human6));

        mapSchema.clear();
    }
}
