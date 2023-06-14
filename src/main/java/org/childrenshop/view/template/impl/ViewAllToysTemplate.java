package org.childrenshop.view.template.impl;

import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.ArrayList;
import java.util.Comparator;

public class ViewAllToysTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nView all toys");
        var toys = toyService.findAll();

        if(toys.isEmpty()){
            ui.output("\nNo toys found\n");
            ui.pressEnterToContinue();
            return;
        }
        var sortedToys = new ArrayList<Toy>(toys);
        sortedToys.sort(Comparator.comparing(Toy::id));
        sortedToys.forEach(toy -> ui.output("ID: " + toy.id() + " ; Toy name: " + toy.name() + " ; " + "Toy heft: " + toy.heft()));
        ui.pressEnterToContinue();
    }
}
