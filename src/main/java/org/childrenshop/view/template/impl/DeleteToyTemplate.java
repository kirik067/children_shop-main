package org.childrenshop.view.template.impl;

import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class DeleteToyTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nDelete Toy");

        int id = 0;
        while (true){
            Optional<Integer> idOptional = ui.input("\nEnter toy ID or an empty string to cancel: ", Integer::parseInt);
            if (idOptional.isEmpty()) {
                ui.output("\nCancelled\n");
                return;
            }

            if (idOptional.get() < 0) {
                ui.output("Value must be greater than or equal to zero! Try Again");
                continue;
            }

            id = idOptional.get();

            break;
        }

        var toyOptional = toyService.findById(id);
        if(toyOptional.isEmpty()){
            ui.output("\nNo note found\n");
            ui.pressEnterToContinue();
            return;
        }

        Toy toy = toyOptional.get();

        ui.output("\nID: " + toy.id() + "\nName: " + toy.name() + "\nHeft: " + toy.heft());

        Optional<String> answer = ui.input("\nDo you want to delete a toy? (1 - yes / any other key - no): ", String::toString);

        if (answer.isEmpty() || !answer.get().equals("1")) {
            ui.output("\nCancelled\n");
            ui.pressEnterToContinue();
            return;
        }

        toyService.delete(toy);

        ui.output("\ndone");
        ui.pressEnterToContinue();
    }
}
