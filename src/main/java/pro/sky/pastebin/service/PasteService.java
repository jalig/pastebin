package pro.sky.pastebin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pro.sky.pastebin.dto.CreatePaste;
import pro.sky.pastebin.dto.PasteView;
import pro.sky.pastebin.exception.BadRequestException;
import pro.sky.pastebin.exception.NotFoundException;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;
import pro.sky.pastebin.repository.PasteRepository;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static pro.sky.pastebin.repository.specification.PasteSpecification.*;

@Service
@Slf4j
public class PasteService {

    private final PasteRepository pasteRepository;

    @Value("${pastebin.url}")
    private String address;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    private static String generateRandomUrl() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public String createPaste(CreatePaste createPaste) {
        if (createPaste == null) throw new BadRequestException("Entity is null");
        Paste paste = createPaste.toPaste();
        paste.setUrl(address + generateRandomUrl());
        pasteRepository.save(paste);
        log.info("Paste {} with expired time {} and status {} created ", createPaste, paste.getExpiredTime(), paste.getStatus());
        return paste.getUrl();
    }

    public List<PasteView> findAllPublicPaste() {
        List<PasteView> pasts = pasteRepository
                .findTop10ByStatusAndExpiredTimeIsAfterOrderByCreationTimeDesc(PasteStatus.PUBLIC, Instant.now())
                .stream()
                .map(PasteView::fromPaste)
                .collect(Collectors.toList());
        if (pasts.isEmpty()) throw new NotFoundException("Not found");
        log.info("List of ten pastes loaded");
        return pasts;


    }

    public PasteView findByUrl(String url) {
        if (url == null || url.isBlank()) throw new NotFoundException("URL incorrect");
        PasteView pasteView = PasteView.fromPaste(pasteRepository.findAllByUrlLikeAndExpiredTimeIsAfter(url, Instant.now()));
        log.info("Found paste {}", pasteView);
        return pasteView;
    }

    public List<PasteView> findByTitleOrBody(String title, String body) {
        if ((title == null || title.isBlank()) && (body == null || body.isBlank()))
            throw new NotFoundException("No matches");
        List<PasteView> pasts = pasteRepository
                .findAll(Specification.where(byTitle(title).and(byBody(body).and(byTime()).and(byStatus()))))
                .stream()
                .map(PasteView::fromPaste)
                .collect(Collectors.toList());
        log.info("List of pastes loaded with title {} and/or body {}", title, body);
        if (pasts.isEmpty()) throw new NotFoundException("Not found");
        return pasts;
    }


}