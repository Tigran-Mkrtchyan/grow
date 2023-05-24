package com.grow.workshop_infra.repository;

import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.common_infra.repository.JPARepository;
import com.grow.workshop_core.domain.aggregate.WorkshopAggregate;
import org.springframework.stereotype.Repository;

@Repository("workshopAggregateRepository")
public interface WorkshopAggregateRepository extends JPARepository<WorkshopAggregate, WorkshopId> {
}
