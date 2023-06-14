package org.childrenshop.view.template.impl;

import org.childrenshop.model.Toy;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class ToyLotteryTemplate implements Template {
    @Override
    public void output() {
        ui.output("\nToy lottery");

        if (stockService.findAll().size() - lotteryService.countWonToys() <= 0) {
            ui.output("\nOut of stock\n");
            ui.output("\nlottery is cancelled\n");
            ui.pressEnterToContinue();
            return;
        }

        Optional<String> answer = ui.input("\nStart lottery? (1 - yes / any other key - no): ", String::toString);
        if (answer.isEmpty() || !answer.get().equals("1")) {
            ui.output("\nCancelled\n");
            ui.pressEnterToContinue();
            return;
        }

        int toyId = lotteryService.holdLottery();

        Toy toy = toyService.findById(toyId).get();
        ui.output("\nPrize: " + toy.name());
        ui.output("\nThe toy is awaiting receipt by the winner\n");
        ui.pressEnterToContinue();
    }
}
