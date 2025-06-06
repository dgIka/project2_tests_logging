import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    public void constructor_throwsIllegalArgumentExceptionWithNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void constructor_throwsIllegalArgumentExceptionWithContainsMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void constructor_throwsIllegalArgumentExceptionWithEmptyString() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }
    @Test
    public void constructor_throwsIllegalArgumentExceptionWithMessage() {
        try {
            List<Horse> horses = new ArrayList<>();
            new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesShouldReturnSameListOfHorses() {
        List<Horse> horses = IntStream.range(0, 30)
                .mapToObj(d -> mock(Horse.class))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void shouldUseMoveForEachHorse() {
        List<Horse> horses = IntStream.range(0, 30)
                .mapToObj(d -> mock(Horse.class))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse a : horses) {
            verify(a, times(1)).move();
        }
    }

    @Test
    public void shouldReturnHorseWithMaxDistance() {
        List<Horse> horses = IntStream.range(0, 3)
                .mapToObj(d -> mock(Horse.class))
                .collect(Collectors.toList());
        for (int i = 0; i < 3; i++) {
            when(horses.get(i).getDistance()).thenReturn((double)i);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        double testWinner = horses.stream().max(Comparator.comparing(Horse::getDistance)).get().getDistance();
        assertEquals(testWinner, hippodrome.getWinner().getDistance());
    }


}
