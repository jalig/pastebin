package pro.sky.pastebin.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.pastebin.repository.PasteRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Scheduler {

    private final PasteRepository pasteRepository;

    public Scheduler(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    @Scheduled(fixedRateString = "${pastebin.timer.rate-minutes}", timeUnit = TimeUnit.MINUTES)
    @Transactional
    public void clearTokens() {
        log.info("Removal of expired pastes");
        pasteRepository.deleteAllByExpiredTimeIsBefore(Instant.now());
    }
}