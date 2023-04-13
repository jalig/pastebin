package pro.sky.pastebin.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.pastebin.dto.PasteDTO;
import pro.sky.pastebin.dto.PasteView;
import pro.sky.pastebin.model.enums.PasteStatus;
import pro.sky.pastebin.model.enums.TimePaste;
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
    public String createPaste(
            @RequestBody PasteDTO pasteDTO,
            @RequestParam TimePaste timePaste,
            @RequestParam PasteStatus pasteStatus

    ) {
         return "http://my-awesome-pastebin.tld/" + pasteService.createPaste(pasteDTO, timePaste, pasteStatus);
    }

    @GetMapping(value = "/last-ten")
    public List<PasteView> findAllPublic() {
        return pasteService.findAllPublicPaste();
    }

    @GetMapping(value = "/{url}")
    public PasteView findByUrl(@PathVariable String url) {
        return pasteService.findByUrl(url);
    }

    @GetMapping
    public List<PasteView> findByTitleOrBody(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return pasteService.findByTitleOrBody(title, body);

    }
}
