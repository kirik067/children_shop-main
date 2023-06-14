package org.childrenshop.view.template.impl;

import org.childrenshop.model.StockBalance;
import org.childrenshop.view.template.Template;

import java.util.ArrayList;
import java.util.Comparator;

public class StockbalanceTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nStock balance\n");
        var balance = stockService.findAll();

        if(balance.isEmpty()){
            ui.output("\nOut of stock\n");
            ui.pressEnterToContinue();
            return;
        }

        var sortedBalance = new ArrayList<StockBalance>(balance);
        sortedBalance.sort(Comparator.comparing(StockBalance::toyId));
        sortedBalance.forEach(position -> ui.output("Toy ID: " + position.toyId() +
                " ; Toy name: " + toyService.findById(position.toyId()).get().name() +
                " ; Quantity: " + position.quantity()));
        ui.pressEnterToContinue();
    }
}