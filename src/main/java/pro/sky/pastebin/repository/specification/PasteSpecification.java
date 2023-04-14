package pro.sky.pastebin.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import pro.sky.pastebin.model.Paste;
import pro.sky.pastebin.model.enums.PasteStatus;

public class PasteSpecification {
    public static Specification<Paste> byTitle(String title) {

        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)) {
                return criteriaBuilder.conjunction();
            }
            criteriaBuilder.equal(root.get("title"), title);
            return criteriaBuilder.equal(root.get("status"), PasteStatus.PUBLIC);
        };

    }

    public static Specification<Paste> byBody(String body) {

        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(body)) {
                return criteriaBuilder.conjunction();
            }
            criteriaBuilder.equal(root.get("body"),  "%" + body + "%");
            return criteriaBuilder.equal(root.get("status"), PasteStatus.PUBLIC);
        };

    }





}
