import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HippodromeTest {
    private static List<Horse> horses;
    private static Hippodrome hippodrome;

    @BeforeAll
    static void initMethod() {
        horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Лошадь #" + i, i, i));
        }
        hippodrome = new Hippodrome(horses);
    }

    @ParameterizedTest
    @DisplayName("Тест параметра horses конструктора класса Hippodrome")
    @Order(1)
    @MethodSource("getStreamHorses")
    void constructorHippodromeTest(List<Horse> list) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
        if (list == null) {
            assertEquals("Horses cannot be null.", exception.getMessage());
        } else assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    static Stream<List<Horse>> getStreamHorses() {
        return Stream.of(null, new ArrayList<>());
    }

    @Test
    @DisplayName("Тест метода getHorses")
    @Order(2)
    void getHorsesTest() {
        for (int i = 0; i < hippodrome.getHorses().size(); i++) {
            assertSame(horses.get(i), hippodrome.getHorses().get(i));
        }
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    @DisplayName("Тест метода move")
    @Order(3)
    void moveTest() {
    }

    @Test
    @DisplayName("Тест метода getWinner")
    @Order(4)
    void getWinnerTest() {
        assertEquals(30, hippodrome.getWinner().getDistance());
    }
}