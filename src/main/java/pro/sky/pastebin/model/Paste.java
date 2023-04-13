package pro.sky.pastebin.model;

import lombok.Getter;
import lombok.Setter;
import pro.sky.pastebin.model.enums.PasteStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;


@Entity
@Getter
@Setter
public class Paste {

    @Id
    private String url;
    private String body;
    private String title;
    private Instant expiredTime;
    @Enumerated(EnumType.STRING)
    private PasteStatus status;
    private Instant creationTime = Instant.now();

}
