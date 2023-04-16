package pro.sky.pastebin.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import pro.sky.pastebin.dto.CreatePaste;
import pro.sky.pastebin.dto.PasteView;
import pro.sky.pastebin.service.PasteService;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin")
public class PasteController {


    private final PasteService pasteService;

    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping
    @Operation(
            summary = "Создание нового Paste",
            description = "Время задается фиксированно: TEN_MIN, ONE_HOUR, THREE_HOUR, ONE_DAY, ONE_WEEK, ONE_MONTH INFINITY " +
            "Типы статусов: PUBLIC, UNLISTED"

    )
    public String createPaste(@RequestBody CreatePaste createPaste) {
        return pasteService.createPaste(createPaste);
    }

    @GetMapping(value = "/last-ten")
    @Operation(
            summary = "Получить информацию о 10 Paste",
            description = "Получить информацию о 10 самых новых Paste"
    )
    public List<PasteView> findAllPublic() {
        return pasteService.findAllPublicPaste();
    }

    @GetMapping(value = "/{url}")
    @Operation(
            summary = "Получить информацию о Paste по URL",
            description = "Получить информацию о Paste по URL (который мы получаем при создании Paste)"
    )
    public PasteView findByUrl(@PathVariable String url) {
        return pasteService.findByUrl(url);
    }

    @GetMapping
    @Operation(
            summary = "Получить информацию о Paste по title или body",
            description = "Получить информацию о Paste по заголовку или телу Paste"
    )
    public List<PasteView> findByTitleOrBody(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return pasteService.findByTitleOrBody(title, body);

    }
}
