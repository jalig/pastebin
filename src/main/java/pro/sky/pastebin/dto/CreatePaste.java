package pro.sky.pastebin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;
import pro.sky.pastebin.model.enums.TimePaste;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Schema(description = "Полная информация о Paste")
public class CreatePaste {

    @Schema(description = "Тело", example = "body")
    private String body;
    @Schema(description = "Заголовок", example = "title")
    private String title;
    @Enumerated
    @Schema(description = "Время жизни")
    private TimePaste expiredTime;
    @Enumerated(EnumType.STRING)
    @Schema(description = "Статус")
    private PasteStatus status;


    public Paste toPaste() {
        Paste paste = new Paste();
        paste.setBody(this.getBody());
        paste.setTitle(this.getTitle());
        paste.setExpiredTime(paste.getCreationTime().plus(expiredTime.getDuration()));
        paste.setStatus(this.getStatus());
        return paste;
    }

}
