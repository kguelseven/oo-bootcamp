package unit;

import org.junit.jupiter.api.Test;
import probability.Chance;
import rectangle.Rectangle;
import sort.Komparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unit.english.Unit.OUNCE;
import static unit.english.Unit.TABLESPOON;
import static unit.english.Unit.TEASPOON;

public class SorterTest {


    @Test
    void testSort() {
        List<Komparable> rectangleList = Arrays.asList(new Rectangle(10, 20), new Rectangle(30, 10), new Rectangle(3, 4));
        assertEquals(new Rectangle(30, 10), Komparable.max(rectangleList));

        List<Komparable> chanceList = Arrays.asList(new Chance(0.1), new Chance(0.9), new Chance(0.7));
        assertEquals(new Chance(0.9), Komparable.max(chanceList));

        List<Komparable> kitchenList = Arrays.asList(TEASPOON.s(8), TABLESPOON.s(8), OUNCE.s(2));
        assertEquals(OUNCE.s(4), Komparable.max(kitchenList));

    }

    @Test
    void testSortMixed() {
        List<Komparable> komparableList = Arrays.asList(new Rectangle(10, 20), new Chance(0.3));
        assertThrows(ClassCastException.class, () -> Komparable.max(komparableList));
    }

    @Test
    void testSortEmptyList() {
        assertNull(Komparable.max(new ArrayList<Komparable>()));
    }

}
