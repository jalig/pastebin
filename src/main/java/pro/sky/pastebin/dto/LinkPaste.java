package pro.sky.pastebin.dto;

import lombok.Getter;
import lombok.Setter;
import pro.sky.pastebin.model.Paste;

@Getter
@Setter
public class LinkPaste {

    private String url;

    public static LinkPaste fromPaste(Paste paste) {
        LinkPaste dto = new LinkPaste();
        dto.setUrl(paste.getUrl());
        return dto;
    }

    public PasteDTO toPaste() {
        PasteDTO paste = new PasteDTO();
        paste.setUrl(this.getUrl());
        return paste;
    }


}
