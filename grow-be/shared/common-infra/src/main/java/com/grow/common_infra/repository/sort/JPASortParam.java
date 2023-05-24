package com.grow.common_infra.repository.sort;

import com.grow.kernel.domain.base.AbstractEntity;
import com.grow.kernel.domain.base.DomainObjectId;
import org.springframework.data.domain.Sort;

public interface JPASortParam<T extends AbstractEntity<ID>, ID extends DomainObjectId> {

    Sort build();

}
