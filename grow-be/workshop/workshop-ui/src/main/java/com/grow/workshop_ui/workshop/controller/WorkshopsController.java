package com.grow.workshop_ui.workshop.controller;

import an.awesome.pipelinr.Pipeline;
import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.workshop_ui.workshop.command.CreateWorkshopCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/workshops")
public class WorkshopsController {
    private final Pipeline commandPipeline;
    private final Pipeline queryPipeline;

    public WorkshopsController(@Qualifier(value = "commandPipeline") Pipeline commandPipeline,
                               @Qualifier(value = "queryPipeline") Pipeline queryPipeline) {
        this.commandPipeline = commandPipeline;
        this.queryPipeline = queryPipeline;
    }

    @PostMapping()
    public ResponseEntity<WorkshopId> createWorkshop(@RequestBody CreateWorkshopCommand command) {

        return ResponseEntity.ok(commandPipeline.send(command));
    }
}
