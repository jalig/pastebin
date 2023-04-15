package pro.sky.pastebin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import pro.sky.pastebin.model.Paste;

@Getter
@Setter
@Schema(description = "Класс для отображения Paste")
public class PasteView {

    @Schema(description = "Адрес", example = "url")
    private String url;
    @Schema(description = "Тело", example = "body")
    private String body;
    @Schema(description = "Заголовок", example = "title")
    private String title;



    public static PasteView fromPaste(Paste paste) {
        PasteView dto = new PasteView();
        dto.setUrl(paste.getUrl());
        dto.setBody(paste.getBody());
        dto.setTitle(paste.getTitle());
        return dto;
    }

}
