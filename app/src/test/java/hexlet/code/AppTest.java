package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void test1() {
        String actual = App.test();
        assertEquals("test", actual);
    }
}
