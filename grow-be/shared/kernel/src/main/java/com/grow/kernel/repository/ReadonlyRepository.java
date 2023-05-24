package com.grow.kernel.repository;

import com.grow.kernel.domain.base.AbstractEntity;
import com.grow.kernel.domain.base.DomainObjectId;
import com.grow.kernel.exception.DomainObjectNotFoundException;
import com.grow.kernel.repository.page.Page;
import com.grow.kernel.repository.page.PageDetails;
import com.grow.kernel.repository.sort.AbstractSortParam;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

public interface ReadonlyRepository<T extends AbstractEntity<ID>, ID extends DomainObjectId> {

    Optional<T> findById(ID id);

    Collection<T> findAll();

    Collection<T> findAll(AbstractSortParam<T, ID> sortParam);

    Collection<T> findBySpec(AbstractSpecification<T, ID> spec);

    Page<T> findAll(AbstractSpecification<T, ID> spec,
                    AbstractSortParam<T, ID> sortParam,
                    PageDetails pageDetails);

    Page<T> findAll(AbstractSpecification<T, ID> spec,
                    PageDetails pageDetails);

    Collection<T> findAll(AbstractSpecification<T, ID> spec,
                          AbstractSortParam<T, ID> sortParam);

    Collection<T> findBySpec(AbstractSpecification<T, ID> spec, AbstractSortParam<T, ID> sortParam);

    long count(AbstractSpecification<T, ID> spec);

    boolean exists(AbstractSpecification<T, ID> spec);

    default T retrieveById(ID id) {
        return this.findById(id)
                .orElseThrow(() -> new DomainObjectNotFoundException(id));
    }

    default Optional<T> findOneBySpec(AbstractSpecification<T, ID> spec) {
        Collection<T> bySpec = this.findBySpec(spec);
        if (CollectionUtils.isEmpty(bySpec)) {
            return Optional.empty();
        }
        if (bySpec.size() > 1) {
            throw new RuntimeException("Requested findOneBySpec but found more than one");
        }
        return bySpec.stream().findAny();
    }

    default T retrieveOneBySpec(AbstractSpecification<T, ID> spec) {
        return this.findOneBySpec(spec)
                .orElseThrow(DomainObjectNotFoundException::new);
    }

}
