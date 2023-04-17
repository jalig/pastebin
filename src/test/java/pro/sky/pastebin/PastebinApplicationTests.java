package pro.sky.pastebin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import pro.sky.pastebin.config.DockerConfig;

@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PastebinApplicationTests extends DockerConfig {

    @Test
    void contextLoads() {
    }

}
