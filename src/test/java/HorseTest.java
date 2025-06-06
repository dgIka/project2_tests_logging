import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void constructor_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4, 7));
    }
    @Test
    public void constructor_exceptionContainsMessageNullName() {
        try {
            new Horse(null, 2.4, 7);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructor_withEmptyStringArgumentShouldThrowIllegalArgumentException (String arg) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 2.4, 7));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructor_exceptionContainsMessageBlankName(String arg) {
        try {
            new Horse(arg, 2.4, 7);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }

    }
    @Test
    public void constructor_withNegativeSecondArgShouldThrowIllegalArgumentException () {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -2.4, 7));
    }

    @Test
    public void constructor_exceptionContainsMessageNegativeSpeed() {
        try {
            new Horse("Horse", -2.4, 7);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }

    }


    @Test
    public void constructor_withNegativeThirdArgShouldThrowIllegalArgumentException () {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 2.4, -7));
    }

    @Test
    public void constructor_exceptionContainsMessageNegativeDistance() {
        try {
            new Horse("Horse", 2.4, -7);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }

    }
    @Test
    public void getNameReturnsFirstArgument() {
        Horse horse = new Horse("Horse", 2.4, 7);
        assertEquals("Horse", horse.getName());
    }
    @Test
    public void getSpeedReturnsSecondArgument() {
        Horse horse = new Horse("Horse", 2.4, 7);
        assertEquals(2.4, horse.getSpeed());
    }
    @Test
    public void getDistanceReturnsThirdArgument() {
        Horse horse = new Horse("Horse", 2.4, 7);
        assertEquals(7, horse.getDistance());
    }
    @Test
    public void getDistanceReturnsZeroWithoutArguments() {
        Horse horse = new Horse("Horse", 2.4);
        assertEquals(0, horse.getDistance());
    }
    @Test
    public void moveUses_getRandomDoubleWithParameters () {
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(1.0);
            Horse horse = new Horse("Horse", 2.4, 7.0);
            Double testResult = horse.getSpeed() * 1.0 + horse.getDistance();
            horse.move();
            assertEquals(testResult, horse.getDistance());
        }

    }
    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4, 5})
    public void moveUses_getRandomDoubleWIthSpecificFormula(double arg) {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(arg);
            Horse horse = new Horse("Horse", 2.4, 7.0);
            Double testValue = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(testValue, horse.getDistance());
        }
    }


}
