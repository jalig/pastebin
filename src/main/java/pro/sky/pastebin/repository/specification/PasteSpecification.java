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
            return criteriaBuilder.equal(root.get("title"), title);
        };
    }


    public static Specification<Paste> byTimeAndStatus() {
        return (root, query, criteriaBuilder) -> {

            Predicate expiredTime = criteriaBuilder.greaterThanOrEqualTo(root.get("expiredTime"), Instant.now());
            Predicate status = criteriaBuilder.equal(root.get("status"), PasteStatus.PUBLIC);
            return criteriaBuilder.and(expiredTime, status);
        };
    }


    public static Specification<Paste> byBody(String body) {

        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(body)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("body"), "%" + body + "%");
        };
    }

}
