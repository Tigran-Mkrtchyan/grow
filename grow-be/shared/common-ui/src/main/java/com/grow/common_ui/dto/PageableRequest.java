package com.grow.common_ui.dto;

import com.grow.common_ui.command.Command;

public class PageableRequest<R> extends Command<R> {

    private final int size;
    private final int page;

    public PageableRequest(Integer size,
                           Integer page) {
        super();
        this.size = size != null ? size : 10;
        this.page = page != null ? page : 0;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getPage() {
        return page;
    }
}
