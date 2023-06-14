package org.childrenshop.view.template.impl;

import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class EditToyTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nEdit Toy");

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
            ui.output("\nNo toy found\n");
            ui.pressEnterToContinue();
            return;
        }

        Toy toy = toyOptional.get();

        ui.output("\nID: " + toy.id() + "\nName: " + toy.name() + "\nHeft: " + toy.heft());

        Optional<String> nameOptional = ui.input("\nEnter toy name or an empty string to skip: ", String::toString);
        String name = toy.name();
        if(nameOptional.isPresent()){
            name = nameOptional.get();
        }

        int heft = 0;
        while (true) {
            Optional<Integer> heftOptional = ui.input("\nEnter heft in percent or an empty string to skip: ", Integer::parseInt);

            if (heftOptional.isEmpty()) {
                heft = toy.heft();
                break;
            }

            if (heftOptional.get() < 1 || heftOptional.get() > 100) {
                ui.output("Value must be between 0 and 100! Try Again");
                continue;
            }
            heft = heftOptional.get();

            break;
        }

        if (name == toy.name() && heft == toy.heft()){
            ui.output("\nCancelled\n");
            return;
        }

        toyService.update(new Toy(id, name, heft));
        ui.output("\ndone");
        ui.pressEnterToContinue();
    }
}
