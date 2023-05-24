package com.grow.common_core.application.scheduler.job;

import com.grow.common_core.application.scheduler.context.Context;
import org.springframework.transaction.annotation.Transactional;

/**
 * Must be Spring component
 */
public interface Job<C extends Context> {

    @Transactional
    void execute(C context);

}
