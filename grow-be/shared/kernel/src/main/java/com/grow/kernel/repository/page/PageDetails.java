package com.grow.kernel.repository.page;

import lombok.Getter;

@Getter
public class PageDetails {

    private int size;
    private int number;

    public PageDetails(Integer size, Integer number) {
        this.setSize(size);
        this.setNumber(number);
    }

    private void setSize(Integer size) {
        if (size == null) {
            throw new IllegalStateException("Page size must not be null");
        }
        if (size < 1) {
            throw new IllegalStateException("Page size must not be less than one");
        }
        this.size = size;
    }

    private void setNumber(Integer number) {
        if (number == null) {
            throw new IllegalStateException("Page number must not be null");
        }
        if (number < 0) {
            throw new IllegalStateException("Page number must not be less than zero");
        }
        this.number = number;
    }

}
