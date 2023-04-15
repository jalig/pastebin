package pro.sky.pastebin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import pro.sky.pastebin.config.DockerConfig;
import pro.sky.pastebin.dto.PasteDTO;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;
import pro.sky.pastebin.model.enums.TimePaste;
import pro.sky.pastebin.repository.PasteRepository;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PasteControllerTest extends DockerConfig {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PasteRepository pasteRepository;
    @Autowired
    ObjectMapper objectMapper;

    Paste paste;

    PasteStatus pasteStatus = PasteStatus.PUBLIC;

    TimePaste timePaste = TimePaste.TEN_MIN;

    @BeforeEach
    void setUp() {
        paste = new Paste();
        paste.setUrl(UUID.randomUUID().toString());
        paste.setTitle("Title");
        paste.setBody("Body");
        paste.setStatus(pasteStatus);
        paste.setCreationTime(Instant.now());
        paste.setExpiredTime(Instant.now().plus(timePaste.getDuration()));
        pasteRepository.save(paste);

    }

    @AfterEach
    void tearDown() {
        pasteRepository.deleteAll();
    }

    @Test
    void createPaste() throws Exception {
        mockMvc.perform(post("/my-awesome-pastebin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PasteDTO.fromPaste(paste)))
                        .param("timePaste", timePaste.toString())
                        .param("pasteStatus", pasteStatus.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString());
    }

    @Test
    void findAllPublic() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin/last-ten"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void findByUrl() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin/" + paste.getUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PasteDTO.fromPaste(paste))))
                .andExpect(status().isOk());
    }

    @Test
    void findByTitleOrBody() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}