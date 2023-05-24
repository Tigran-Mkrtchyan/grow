package com.grow.common_infra.repository;

import com.grow.common_infra.repository.page.JPAPage;
import com.grow.common_infra.repository.sort.JPASortParam;
import com.grow.kernel.domain.base.AbstractAggregateRoot;
import com.grow.kernel.domain.base.DomainObjectId;
import com.grow.kernel.exception.DomainObjectNotFoundException;
import com.grow.kernel.repository.AbstractSpecification;
import com.grow.kernel.repository.Repository;
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
public interface JPARepository
        <T extends AbstractAggregateRoot<ID>, ID extends DomainObjectId>
        extends Repository<T, ID>, JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    Optional<T> findById(ID id);

    void deleteById(ID id);

    @Override
    default Collection<T> findAll(AbstractSpecification<T, ID> spec) {
        return this.findAll(((JPASpecification<T, ID>) spec).build());
    }

    @Override
    default boolean existsBySpec(AbstractSpecification<T, ID> spec) {
        return this.exists(((JPASpecification<T, ID>) spec).build());
    }

    default long count(AbstractSpecification<T, ID> spec) {
        return this.count(((JPASpecification<T, ID>) spec).build());
    }

    @Override
    default T retrieveById(ID id) {
        if (id == null) {
            throw new DomainObjectNotFoundException("Unspecified resource id");
        }
        return this.findById(id)
                .orElseThrow(() -> new DomainObjectNotFoundException(id));
    }

    @Override
    default T save(T aggregate) {
        return this.saveAndFlush(aggregate);
    }

    @Override
    default Collection<T> saveAll(Collection<T> aggregates) {
        return this.saveAllAndFlush(aggregates);
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

    default void delete(T aggregate) {
        this.deleteById(aggregate.id());
        this.flush();
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
