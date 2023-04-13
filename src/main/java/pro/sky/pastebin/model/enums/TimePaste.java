package pro.sky.pastebin.model.enums;

import lombok.Getter;

import java.time.Duration;

@Getter
public enum TimePaste {
    TEN_MIN(Duration.ofMinutes(10)),
    ONE_HOUR(Duration.ofHours(1)),
    THREE_HOUR(Duration.ofHours(3)),
    ONE_DAY(Duration.ofDays(1)),
    ONE_WEEK(Duration.ofDays(7)),
    ONE_MONTH(Duration.ofDays(30)),
    INFINITY(Duration.ofDays(100000));

    private final Duration duration;

    TimePaste(Duration duration) {
        this.duration = duration;
    }
}
