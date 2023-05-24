package com.grow.common_infra.repository.sort;

import com.grow.kernel.repository.sort.SortDirection;
import com.grow.kernel.domain.base.AbstractEntity;
import com.grow.kernel.domain.base.DomainObjectId;
import org.springframework.data.domain.Sort;

public class BaseSortParamBuilder<T extends AbstractEntity<ID>, ID extends DomainObjectId> {

    private Sort sort;
    private SortDirection DEFAULT_DIRECTION;

    public BaseSortParamBuilder() {
        sort = Sort.unsorted();
        DEFAULT_DIRECTION = SortDirection.ASC;
    }

    protected void and(String path) {
        and(path, DEFAULT_DIRECTION);
    }

    protected void and(String path, SortDirection direction) {
        sort = sort.and(Sort.by(map(direction), path));
    }

    private Sort.Direction map(SortDirection direction) {
        if (direction == null) {
            throw new IllegalStateException("Sort direction must not be null");
        }
        if (SortDirection.ASC.equals(direction)) {
            return Sort.Direction.ASC;
        } else if (SortDirection.DESC.equals(direction)) {
            return Sort.Direction.DESC;
        }
        throw new UnsupportedOperationException(
                String.format("Unsupported direction '%s' to construct JPA SortDirection", direction.name()));
    }

    public Sort build() {
        return sort;
    }

}
