package com.grow.common_infra.repository.page;

import com.grow.kernel.repository.page.Page;

import java.util.List;
import java.util.function.Function;

public class JPAPage<T> implements Page<T> {

    private org.springframework.data.domain.Page<T> page;

    public JPAPage(org.springframework.data.domain.Page<T> page) {
        this.setPage(page);
    }

    @Override
    public List<T> getContent() {
        return page.getContent();
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public int getSize() {
        return page.getSize();
    }

    @Override
    public int getNumber() {
        return page.getNumber();
    }

    @Override
    public <U> Page<U> map(Function<T, U> function) {
        return new JPAPage<>(page.map(function));
    }

    private void setPage(org.springframework.data.domain.Page<T> page) {
        if (page == null) {
            throw new IllegalStateException("JPAPage page must not be null");
        }
        this.page = page;
    }

}
