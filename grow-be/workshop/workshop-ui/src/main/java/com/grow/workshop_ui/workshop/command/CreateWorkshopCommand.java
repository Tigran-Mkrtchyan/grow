package com.grow.workshop_ui.workshop.command;


import com.grow.common_core.domain.ids.WorkshopId;
import com.grow.common_core.domain.value_objects.Text;
import com.grow.common_ui.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkshopCommand extends Command<WorkshopId> {
    private Text title;
}
