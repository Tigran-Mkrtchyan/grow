package com.grow.common_core.repository.sort;

import com.grow.kernel.domain.base.AbstractEntity;
import com.grow.kernel.domain.base.DomainObjectId;
import com.grow.kernel.repository.sort.AbstractSortParam;

public interface SortParamProvider<T extends AbstractEntity<ID>, ID extends DomainObjectId, S extends AbstractSortParam<T, ID>> {

    S provide();

}
