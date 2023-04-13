package pro.sky.pastebin.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pro.sky.pastebin.dto.PasteDTO;
import pro.sky.pastebin.dto.PasteView;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;
import pro.sky.pastebin.model.enums.TimePaste;
import pro.sky.pastebin.repository.PasteRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static pro.sky.pastebin.repository.specification.PasteSpecification.byBody;
import static pro.sky.pastebin.repository.specification.PasteSpecification.byTitle;

@Service
public class PasteService {

    private final PasteRepository pasteRepository;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }


    public String createPaste(PasteDTO pasteDTO, TimePaste timePaste, PasteStatus pasteStatus) {
        Paste paste = pasteDTO.toPaste();
        paste.setUrl(UUID.randomUUID().toString());
        paste.setExpiredTime(Instant.now().plus(timePaste.getDuration()));
        paste.setStatus(pasteStatus);
        pasteRepository.save(paste);
        return paste.getUrl();
    }


    public List<PasteView> findAllPublicPaste() {
        return pasteRepository.findAllByStatusPublic()
                .stream()
                .map(PasteView::fromPaste)
                .collect(Collectors.toList());
    }

    public PasteView findByUrl(String url) {
        return PasteView.fromPaste(pasteRepository.findAllByUrlLike(url));
    }


    public List<PasteView> findByTitleOrBody(String title, String body) {
        return pasteRepository.findAll(Specification.where(byTitle(title).and(byBody(body))))
                .stream()
                .map(PasteView::fromPaste)
                .collect(Collectors.toList());
    }




}
