package org.childrenshop.view.template.impl;
import org.childrenshop.model.StockBalance;
import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class IssueToysTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nIssue of toy\n");
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
            ui.output("\nOut of stock");
            ui.pressEnterToContinue();
            return;
        }

        ui.output("\nCurrent quantity: " + positionOpt.get().quantity());


        int quantity = 0;
        int stockBalanceQuantity = 0;
        while (true) {
            Optional<Integer> quantityOptional = ui.input("\nEnter issue quantity or an empty string to cancel: ", Integer::parseInt);
            if (quantityOptional.isEmpty()) {
                ui.output("\nCancelled\n");
                return;
            }

            if (quantityOptional.get() < 1 ) {
                ui.output("\nValue must be greater than zero! Try Again");
                continue;
            }

            quantity = quantityOptional.get();
            stockBalanceQuantity = stockService.findById(toy.id()).get().quantity();

            if(quantity > stockBalanceQuantity){
                ui.output("\nThere are not enough! Try Again");
                continue;
            }

            break;
        }

        if(quantity == stockBalanceQuantity){
            stockService.delete(positionOpt.get());
        }else{
            stockService.update(new StockBalance(positionOpt.get().toyId(),stockBalanceQuantity - quantity));
        }

        ui.output("\nToy issued, stock balance: " + (stockBalanceQuantity - quantity));

        ui.pressEnterToContinue();
    }
}
