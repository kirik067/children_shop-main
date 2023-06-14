package org.childrenshop.view.template.impl;

import org.childrenshop.model.StockBalance;
import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class ReceiptToyTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nReceipt Toy\n");
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

        Optional<StockBalance> positionOpt = stockService.findById(id);
        if(positionOpt.isEmpty()){
            ui.output("\nout of stock");
        } else{
            ui.output("\nCurrent quantity: " + positionOpt.get().quantity());
        }

        int quantity = 0;
        while (true) {
            Optional<Integer> quantityOptional = ui.input("\nEnter quantity or an empty string to cancel: ", Integer::parseInt);
            if (quantityOptional.isEmpty()) {
                ui.output("\nCancelled\n");
                return;
            }

            if (quantityOptional.get() < 1 ) {
                ui.output("\nValue must be greater than zero! Try Again");
                continue;
            }

            quantity = quantityOptional.get();

            break;
        }

        var newPosition = new StockBalance(id, quantity);

        if(positionOpt.isEmpty()){
            stockService.add(newPosition);
        } else{
            stockService.update(newPosition);
        }

        ui.output("\nToy receipted\n");
        ui.pressEnterToContinue();
    }
}
