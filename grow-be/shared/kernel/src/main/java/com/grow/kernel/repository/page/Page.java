package com.grow.kernel.repository.page;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.function.Function;

public interface Page<T> {

    List<T> getContent();

    long getTotalElements();

    int getTotalPages();

    int getSize();

    int getNumber();

    <U> Page<U> map(Function<T, U> function);

    static <T> Page<T> empty() {
        return new Page<T>() {
            @Override
            public List<T> getContent() {
                return List.of();
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<T, U> function) {
                throw new NotImplementedException();
            }
        };
    }

}
