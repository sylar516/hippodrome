import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HippodromeTest {
    @Test
    @DisplayName("���� ����������� - �������� horses �� ��������� null")
    @Order(1)
    void constructorHorsesIsNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("���� ����������� - �������� horses - ������ ������")
    @Order(2)
    void constructorHorsesIsEmptyTest() {
        List<Horse> list = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("���� ������ getHorses")
    @Order(3)
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("������ #" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("���� ������ move")
    @Order(4)
    void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse, times(1)).move();
        }
    }

    @Test
    @DisplayName("���� ������ getWinner")
    @Order(5)
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("������ �1", 1, 10));
        horses.add(new Horse("������ �2", 1, 5));
        horses.add(new Horse("������ �3", 1, 1));
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.get(0), hippodrome.getWinner());
    }
}