package pro.sky.pastebin.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.pastebin.model.Paste;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasteRepository extends JpaRepository<Paste, String>, JpaSpecificationExecutor<Paste> {
    @Query(
            value = "select * from paste p where p.status in ('PUBLIC') order by creation_time desc limit 10",
            nativeQuery = true)
    List<Paste> findAllByStatusPublic();

    Paste findAllByUrlLike(String url);

    @Override
    List<Paste> findAll(Specification<Paste> specification);

    void deleteAllByExpiredTimeIsBefore(Instant now);



}
