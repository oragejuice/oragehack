import me.oragejuice.commandDispatcher.DamerauLevenshtein;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuzzyTest {

    @Test
    @DisplayName("spacing difference")
    public void levenshtienDiff(){
        assertEquals(0, DamerauLevenshtein.calculate("test", "test"));
        assertEquals(1, DamerauLevenshtein.calculate("Test", "test"));
        assertEquals(1, DamerauLevenshtein.calculate("test", "testy"));
        assertEquals(1, DamerauLevenshtein.calculate("testy", "test"));
        assertEquals(3, DamerauLevenshtein.calculate("test", "testing"));
    }
}
