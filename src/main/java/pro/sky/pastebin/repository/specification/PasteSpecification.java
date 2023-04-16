package pro.sky.pastebin.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;

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

    public static Specification<Paste> byTime() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("expiredTime"), Instant.now());
    }

    public static Specification<Paste> byStatus() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), PasteStatus.PUBLIC);
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
