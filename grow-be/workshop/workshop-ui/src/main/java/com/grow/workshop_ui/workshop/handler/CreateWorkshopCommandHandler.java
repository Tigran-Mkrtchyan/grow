package com.grow.workshop_ui.workshop.handler;

import an.awesome.pipelinr.Command;
import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.kernel.repository.Repository;
import com.grow.workshop_core.domain.aggregate.WorkshopAggregate;
import com.grow.workshop_ui.workshop.command.CreateWorkshopCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateWorkshopCommandHandler implements Command.Handler<CreateWorkshopCommand, WorkshopId> {
    private final Repository<WorkshopAggregate, WorkshopId> workshopRepository;

    @Override
    public WorkshopId handle(CreateWorkshopCommand command) {
        WorkshopAggregate workshop = new WorkshopAggregate(command.getTitle());

        return workshopRepository.save(workshop).id();
    }
}
