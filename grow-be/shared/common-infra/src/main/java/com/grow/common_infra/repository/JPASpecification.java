package com.grow.common_infra.repository;

import org.springframework.data.jpa.domain.Specification;

public interface JPASpecification<T, ID> {

    Specification<T> build();

}
