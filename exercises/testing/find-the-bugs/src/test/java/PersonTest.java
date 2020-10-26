import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

final class PersonTest {
    @Test
    void exampleTest() {
        assertThat(1 + 1, is(2));
    }

    @Test
    void testIfMinor() {
        Person test = new Person("Lili", "Coco", 17);
        assertThat(test.isMinor(), is(true));
    }
}
