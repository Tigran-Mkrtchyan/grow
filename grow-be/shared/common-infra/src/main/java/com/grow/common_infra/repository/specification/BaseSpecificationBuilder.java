package com.grow.common_infra.repository.specification;

import com.grow.common_infra.repository.JPASpecification;
import org.springframework.data.jpa.domain.Specification;

public class BaseSpecificationBuilder<T, ID>
        implements JPASpecification<T, ID> {

    private Specification<T> specification;

    public BaseSpecificationBuilder() {
        specification = Specification.where(null);
    }

    public Specification<T> build() {
        return this.specification;
    }

    protected void and(Object object, Specification<T> specification) {
        if (object != null) {
            this.specification = this.specification.and(specification);
        }
    }

    protected void and(Specification<T> specification) {
        this.specification = this.specification.and(specification);
    }

    protected void or(Object object, Specification<T> specification) {
        if (object != null) {
            this.specification = this.specification.or(specification);
        }
    }

    protected void or(Specification<T> specification) {
        this.specification = this.specification.or(specification);
    }

    protected String constructSearchPattern(String search) {
        if (search == null) return null;
        String searchValue = escapeSpecialCharacters(search.toLowerCase());
        return String.format("%%%s%%", searchValue);
    }

    private String escapeSpecialCharacters(String source) {
        String result = source;
        result = result.replaceAll("_", "\\\\_");
        return result;
    }

}
