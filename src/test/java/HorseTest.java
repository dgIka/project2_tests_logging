import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Test
    public void constructor_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4, 7));
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -2.4, 7));
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 2.4, -7));
    }
    @Test
    public void constructor_exceptionContainsMessageNullName() {
        Exception d = null;
        try {
            new Horse(null, 2.4, 7);
        } catch (IllegalArgumentException e) {
            d = new IllegalArgumentException("Name cannot be null");
        }
        assertEquals("Name cannot be null", d.getMessage());
    }

    @Test
    public void constructor_exceptionContainsMessageBlankName() {
        Exception d = null;
        try {
            new Horse(" ", 2.4, 7);
        } catch (IllegalArgumentException e) {
            d = new IllegalArgumentException("Name cannot be blank");
        }
        assertEquals("Name cannot be blank", d.getMessage());
    }
    

}
