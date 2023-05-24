package com.grow.common_core.repository.specification;

import com.grow.kernel.domain.base.AbstractEntity;
import com.grow.kernel.domain.base.DomainObjectId;
import com.grow.kernel.repository.AbstractSpecification;

public interface SpecificationProvider<T extends AbstractEntity<ID>, ID extends DomainObjectId, S extends AbstractSpecification<T, ID>> {
    S provide();
}
