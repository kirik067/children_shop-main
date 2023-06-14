package org.childrenshop.view.template.impl;

import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class AddToyTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nAdd Toy\n");
        ui.output("Complete the following fields or enter an empty string to cancel\n");

        Optional<String> nameOptional = ui.input("Toy name: ", String::toString);
        if(nameOptional.isEmpty()){
            ui.output("\nCancelled\n");
            return;
        }

        int heft = 0;
        while (true) {
            Optional<Integer> heftOptional = ui.input("Heft, percent: ", Integer::parseInt);
            if (heftOptional.isEmpty()) {
                ui.output("\nCancelled\n");
                return;
            }

            if (heftOptional.get() < 1 || heftOptional.get() > 100) {
                ui.output("Value must be between 0 and 100! Try Again");
                continue;
            }
            heft = heftOptional.get();

            break;
        }

        var toys = toyService.findAll();
        int id = 0;
        if(toys.size() != 0){
            id = toys.stream().max((toy1, toy2) -> toy1.id() - toy2.id()).get().id() + 1;
        }

        var toy = new Toy(id, nameOptional.get(), heft);

        toyService.add(toy);
        ui.output("\nToy added\n");
        ui.pressEnterToContinue();
    }
}
