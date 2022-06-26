import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Disabled
    @Test
    @DisplayName("Тест метода main")
    @Timeout(value = 22)
    void mainTest() throws Exception {
        Main.main(null);
    }
}