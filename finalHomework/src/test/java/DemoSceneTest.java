import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.DemoScene;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Тест для {@link DemoScene}.
 */
public class DemoSceneTest {
    /**
     * Передаёт в демо-класс файлы и проверяет отрабатывает ли исполняемый файл.
     * @throws IOException
     */
    @Test
    void demo() throws IOException {
        Path trafficPath = Paths.get("src/test/resources/traffic.xml");
        Path accidentPath = Paths.get("src/test/resources/accidents.xml");
        DemoScene demoScene = new DemoScene();
        Assertions.assertTrue(demoScene.demo(trafficPath, accidentPath));
    }
}
