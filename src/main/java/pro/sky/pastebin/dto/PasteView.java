package pro.sky.pastebin.dto;

import lombok.Getter;
import lombok.Setter;
import pro.sky.pastebin.model.Paste;

@Getter
@Setter
public class PasteView {

    private String url;
    private String title;
    private String body;



    public static PasteView fromPaste(Paste paste) {
        PasteView dto = new PasteView();
        dto.setUrl(paste.getUrl());
        dto.setBody(paste.getBody());
        dto.setTitle(paste.getTitle());
        return dto;
    }

}
