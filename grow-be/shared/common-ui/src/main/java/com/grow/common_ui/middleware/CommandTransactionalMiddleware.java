package com.grow.common_ui.middleware;

import an.awesome.pipelinr.Command;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@AllArgsConstructor
public class CommandTransactionalMiddleware implements Command.Middleware {

    private CommandTransactionSynchronization commandTransactionSynchronization;
    @Order(2)
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        TransactionSynchronizationManager.registerSynchronization(commandTransactionSynchronization);
        return next.invoke();
    }

}