package pro.sky.pastebin.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, String>, JpaSpecificationExecutor<Paste> {
    List<Paste> findTop10ByStatusAndExpiredTimeIsAfterOrderByCreationTimeDesc(PasteStatus pasteStatus, Instant time);

    Paste findAllByUrlLikeAndExpiredTimeIsAfter(String url, Instant time);

    @Override
    List<Paste> findAll(Specification<Paste> specification);

    void deleteAllByExpiredTimeIsBefore(Instant now);


}
