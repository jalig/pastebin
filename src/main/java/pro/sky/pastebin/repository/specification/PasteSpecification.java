package pro.sky.pastebin.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;

import javax.persistence.criteria.Predicate;
import java.time.Instant;

public class PasteSpecification {

    public static Specification<Paste> byTitle(String title) {

        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)) {
                return criteriaBuilder.conjunction();
            }
            Predicate getTitle = criteriaBuilder.equal(root.get("title"), title);
            Predicate expiredTime = criteriaBuilder.greaterThanOrEqualTo(root.get("expiredTime"), Instant.now());
            Predicate status = criteriaBuilder.equal(root.get("status"), PasteStatus.PUBLIC);
            return criteriaBuilder.and(getTitle, expiredTime, status);
        };

    }

    public static Specification<Paste> byBody(String body) {

        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(body)) {
                return criteriaBuilder.conjunction();
            }
            Predicate getBody = criteriaBuilder.like(root.get("body"), "%" + body + "%");
            Predicate expiredTime = criteriaBuilder.greaterThanOrEqualTo(root.get("expiredTime"), Instant.now());
            Predicate status = criteriaBuilder.equal(root.get("status"), PasteStatus.PUBLIC);
            return criteriaBuilder.and(getBody, expiredTime, status);
        };

    }


}
