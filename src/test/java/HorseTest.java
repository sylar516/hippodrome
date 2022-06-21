import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HorseTest {
    @ParameterizedTest
    @DisplayName("“естирование первого параметра конструктора Horse")
    @Order(1)
    @ValueSource(strings = {"null", "", "   "})
    public void createHorseFirstParam(String parameter) {
        switch (parameter) {
            case "null" -> {
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
                assertEquals("Name cannot be null.", exception.getMessage());
            }
            case "", "  " -> {
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, 1));
                assertEquals("Name cannot be blank.", exception.getMessage());
            }
        }
    }

    @Test
    @DisplayName("“естирование второго параметра конструктора Horse")
    @Order(2)
    public void createHorseSecondParam() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("“естирование третьего параметра конструктора Horse")
    @Order(3)
    public void createHorseThirdParam() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }
}