package com.grow.common_ui.dto;

import com.grow.common_ui.exception.CommandValidationException;
import com.grow.kernel.repository.sort.SortDirection;
import com.grow.kernel.repository.sort.SortField;

import static com.grow.kernel.repository.sort.SortDirection.DESC;

public class SortableAndPageableRequest<R,T extends SortField> extends PageableRequest<R> {
    private final SortDirection sortDirection;
    private final T sortField;

    public SortableAndPageableRequest(Integer size,
                                      Integer number,
                                      T sortField,
                                      SortDirection sortDirection) {
        super(size, number);
        this.sortField = sortField;
        this.sortDirection = sortDirection != null ? sortDirection : SortDirection.DESC;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public T getSortField() {
        return sortField;
    }

    public Integer getSize() {
        return super.getSize();
    }

    public Integer getPage() {
        return super.getPage();
    }

    protected static SortDirection constructSortDirection(String sort) {
        if (sort == null) return DESC;
        String[] split = sort.split(",");
        if (split.length < 2) return DESC;

        String value = split[1];
        return SortDirection.fromString(value)
                .orElseThrow(() -> new CommandValidationException(
                        String.format("No sort direction found for provided value '%s'", value)));
    }
}
