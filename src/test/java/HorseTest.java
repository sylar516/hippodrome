import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HorseTest {
    @Test
    @DisplayName("Тест констуктора - параметр name со значением null")
    @Order(1)
    public void constructorNameIsNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Тест констуктора - параметр name - пустая строка")
    @Order(2)
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructorNameIsBlankTest(String parameter) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }


    @Test
    @DisplayName("Тест констуктора - параметр speed")
    @Order(3)
    public void constructorHorseSpeedParamTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Тест констуктора - параметр distance")
    @Order(4)
    public void constructorHorseDistanceParamTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Тест метода getName")
    @Order(5)
    void getNameTest() {
        Horse horse = new Horse("Horse", 1);
        assertEquals("Horse", horse.getName());
    }

    @Test
    @DisplayName("Тест метода getSpeed")
    @Order(6)
    void getSpeedTest() {
        Horse horse = new Horse("Horse", 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    @DisplayName("Тест метода getDistance")
    @Order(7)
    void getDistanceTest() {
        Horse horseWithDistance = new Horse("Horse", 1, 1);
        assertEquals(1, horseWithDistance.getDistance());
    }

    @Test
    @DisplayName("Тест метода getDistance с нулевой дистанцией")
    @Order(8)
    void getZeroDistanceTest() {
        Horse horseZeroDistance = new Horse("Horse", 1);
        assertEquals(0, horseZeroDistance.getDistance());
    }

    //написать тест с моками
    @Test
    @DisplayName("Тест метода move - вызов метода GetRandomDouble с параметрами 0.2 и 0.9")
    @Order(9)
    void moveInvokeGetRandomDoubleTest() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 1);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @DisplayName("Тест метода move - изменение значения параметра distance")
    @Order(10)
    @ValueSource(doubles = {0.3, 0.5, 0.8})
    void moveDistanceValueTest(double value) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 2, 2);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);

            double expectedDistance = 2 + 2 * value;
            horse.move();
            assertEquals(expectedDistance, horse.getDistance());
        }
    }

}