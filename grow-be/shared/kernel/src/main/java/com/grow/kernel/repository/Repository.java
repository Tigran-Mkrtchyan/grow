package com.grow.kernel.repository;

import com.grow.kernel.domain.base.AbstractAggregateRoot;
import com.grow.kernel.domain.base.DomainObjectId;
import com.grow.kernel.exception.DomainObjectNotFoundException;
import com.grow.kernel.repository.page.Page;
import com.grow.kernel.repository.page.PageDetails;
import com.grow.kernel.repository.sort.AbstractSortParam;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T extends AbstractAggregateRoot<ID>, ID extends DomainObjectId> {

    Collection<T> findAll(AbstractSpecification<T, ID> spec);

    Collection<T> findAll();

    Page<T> findAll(AbstractSpecification<T, ID> spec,
                    AbstractSortParam<T, ID> sortParam,
                    PageDetails pageDetails);

    Page<T> findAll(AbstractSpecification<T, ID> spec,
                    PageDetails pageDetails);

    boolean existsBySpec(AbstractSpecification<T, ID> spec);

    Optional<T> findById(ID id);

    T retrieveById(ID id);

    T save(T aggregate);

    void delete(T aggregate);

    Collection<T> saveAll(Collection<T> aggregates);

    long count(AbstractSpecification<T, ID> spec);

    default Optional<T> findOne(AbstractSpecification<T, ID> spec) {
        Collection<T> bySpec = this.findAll(spec);
        if (CollectionUtils.isEmpty(bySpec)) {
            return Optional.empty();
        }
        if (bySpec.size() > 1) {
            throw new RuntimeException("Requested findOneBySpec but found more than one");
        }
        return bySpec.stream().findAny();
    }

    default T retrieveOneBySpec(AbstractSpecification<T, ID> spec) {
        return this.findOne(spec)
                .orElseThrow(DomainObjectNotFoundException::new);
    }

}
