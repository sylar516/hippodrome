import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HorseTest {
    private static final Horse horse2Param = new Horse("Horse", 1);
    private static final Horse horse3Param = new Horse("Horse", 1,1);

    @ParameterizedTest
    @DisplayName("“ест параметра name конструктора класса Horse")
    @Order(1)
    @ValueSource(strings = {"null", "", " "})
    public void constructorHorseFirstParamTest(String parameter) {
        switch (parameter) {
            case "null" -> {
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
                assertEquals("Name cannot be null.", exception.getMessage());
            }
            case "", " " -> {
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, 1));
                assertEquals("Name cannot be blank.", exception.getMessage());
            }
        }
    }

    @Test
    @DisplayName("“ест параметра speed конструктора класса Horse")
    @Order(2)
    public void constructorHorseSecondParamTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("“ест параметра distance конструктора класса Horse")
    @Order(3)
    public void constructorHorseThirdParamTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        assertEquals("Horse", horse2Param.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(1, horse2Param.getSpeed());
    }

    @Test
    void getDistanceTest() {
        assertEquals(1, horse3Param.getDistance());
        assertEquals(0, horse2Param.getDistance());
    }

    //написать тест с моками
    @Test
    @ExtendWith(MockitoExtension.class)
    void moveTest(double min, double max) {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(0.5);
            horse2Param.move();
            assertEquals(0.5, horse2Param.getDistance());
        }
    }
}