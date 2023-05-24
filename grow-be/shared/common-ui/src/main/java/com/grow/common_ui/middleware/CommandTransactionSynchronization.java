package com.grow.common_ui.middleware;

import lombok.AllArgsConstructor;
import org.hibernate.FlushMode;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;

import javax.persistence.EntityManager;

@Component
@AllArgsConstructor
public class CommandTransactionSynchronization implements TransactionSynchronization {
    private EntityManager entityManager;

    @Override
    public void beforeCommit(boolean readOnly) {
        SharedSessionContractImplementor session = entityManager.unwrap(SharedSessionContractImplementor.class);
        session.setHibernateFlushMode(FlushMode.MANUAL);
    }
}
