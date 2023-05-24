package com.grow.common_ui.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grow.common_core.util.JsonUtil;

public abstract class Command<R> implements an.awesome.pipelinr.Command<R> {

    @Override
    public String toString() {
        try {
            return String.format("%s:%s", this.getClass().getSimpleName(),
                    JsonUtil.toJson(this));
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}