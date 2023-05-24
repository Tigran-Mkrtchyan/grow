package com.grow.common_infra.repository;

import com.grow.common_infra.repository.page.JPAPage;
import com.grow.common_infra.repository.sort.JPASortParam;
import com.grow.kernel.domain.base.AbstractEntity;
import com.grow.kernel.domain.base.DomainObjectId;
import com.grow.kernel.exception.DomainObjectNotFoundException;
import com.grow.kernel.repository.AbstractSpecification;
import com.grow.kernel.repository.ReadonlyRepository;
import com.grow.kernel.repository.page.Page;
import com.grow.kernel.repository.page.PageDetails;
import com.grow.kernel.repository.sort.AbstractSortParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.Optional;

@NoRepositoryBean
public interface JPAReadOnlyRepository
        <T extends AbstractEntity<ID>, ID extends DomainObjectId>
        extends ReadonlyRepository<T, ID>, JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    Optional<T> findById(ID id);

    @Override
    default Collection<T> findBySpec(AbstractSpecification<T, ID> spec) {
         return this.findAll(((JPASpecification<T, ID>) spec).build());
    }

    @Override
    default T retrieveById(ID id) {
        return this.findById(id)
                .orElseThrow(() -> new DomainObjectNotFoundException(id));
    }

    @Override
    default Collection<T> findAll(AbstractSortParam<T, ID> sortParam) {
        Sort sort = ((JPASortParam<T, ID>) sortParam).build();
        return this.findAll(sort);
    }

    @Override
    default Collection<T> findBySpec(AbstractSpecification<T, ID> spec, AbstractSortParam<T, ID> sortParam) {
        Specification<T> specification = ((JPASpecification<T, ID>) spec).build();
        Sort sort = ((JPASortParam<T, ID>) sortParam).build();
        return this.findAll(specification, sort);
    }

    @Override
    default Page<T> findAll(AbstractSpecification<T, ID> spec, AbstractSortParam<T, ID> sortParam, PageDetails pageDetails) {
        Specification<T> specification = ((JPASpecification<T, ID>) spec).build();
        Sort sort = ((JPASortParam<T, ID>) sortParam).build();
        PageRequest pageRequest = toPageRequest(pageDetails, sort);
        org.springframework.data.domain.Page<T> page = this.findAll(specification, pageRequest);
        return new JPAPage<>(page);

    }

    @Override
    default Page<T> findAll(AbstractSpecification<T, ID> spec, PageDetails pageDetails) {
        Specification<T> specification = ((JPASpecification<T, ID>) spec).build();
        PageRequest pageRequest = toPageRequest(pageDetails);
        org.springframework.data.domain.Page<T> page = this.findAll(specification, pageRequest);
        return new JPAPage<>(page);

    }

    @Override
    default long count(AbstractSpecification<T, ID> spec) {
        Specification<T> specification = ((JPASpecification<T, ID>) spec).build();
        return this.count(specification);
    }

    @Override
    default Collection<T> findAll(AbstractSpecification<T, ID> spec, AbstractSortParam<T, ID> sortParam) {
        Specification<T> specification = ((JPASpecification<T, ID>) spec).build();
        Sort sort = ((JPASortParam<T, ID>) sortParam).build();
        return findAll(specification, sort);
    }

    @Override
    default boolean exists(AbstractSpecification<T, ID> spec) {
        return this.exists(((JPASpecification<T, ID>) spec).build());
    }

    private PageRequest toPageRequest(PageDetails pageDetails, Sort sort) {
        return PageRequest.of(
                pageDetails.getNumber(), pageDetails.getSize(),
                sort);
    }

    private PageRequest toPageRequest(PageDetails pageDetails) {
        return PageRequest.of(
                pageDetails.getNumber(), pageDetails.getSize());
    }
}