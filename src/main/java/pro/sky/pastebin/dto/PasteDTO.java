package pro.sky.pastebin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Getter
@Setter
public class PasteDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String url;
    private String body;
    private String title;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant expiredTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private PasteStatus status;


    public static PasteDTO fromPaste(Paste paste) {
        PasteDTO dto = new PasteDTO();
        dto.setUrl(paste.getUrl());
        dto.setBody(paste.getBody());
        dto.setTitle(paste.getTitle());
        dto.setExpiredTime(paste.getExpiredTime());
        dto.setStatus(paste.getStatus());
        return dto;
    }


    public Paste toPaste() {
        Paste paste = new Paste();
        paste.setUrl(this.getUrl());
        paste.setBody(this.getBody());
        paste.setTitle(this.getTitle());
        paste.setExpiredTime(this.getExpiredTime());
        paste.setStatus(this.getStatus());
        return paste;
    }

}
