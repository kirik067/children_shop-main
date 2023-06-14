package org.childrenshop.view.template.impl;

import org.childrenshop.model.StockBalance;
import org.childrenshop.view.template.Template;

import java.util.Optional;

public class GiveAwayToyTemplate implements Template {

    @Override
    public void output() {
        ui.output("\nGive away a prize toy");

        Optional<Integer> idOptional = lotteryService.peek();
        if (idOptional.isEmpty()){
            ui.output("\nNo prize toys\n");
            ui.pressEnterToContinue();
            return;
        }

        int id = idOptional.get();
        var toyOptional = toyService.findById(id);
        ui.output("\nPrize toy: " + toyOptional.get().name());

        Optional<String> answer = ui.input("\nGive away a prize toy? (1 - yes / any other key - no): ", String::toString);
        if (answer.isEmpty() || !answer.get().equals("1")) {
            ui.output("\nCancelled\n");
            ui.pressEnterToContinue();
            return;
        }

        idOptional = lotteryService.poll();

        int stockBalanceQuantity = stockService.findById(id).get().quantity();

        if(stockBalanceQuantity - 1 == 0){
            stockService.delete(stockService.findById(id).get());
        }else{
            stockService.update(new StockBalance(id,stockBalanceQuantity - 1));
        }

        lotteryService.releaseWonToy(toyOptional.get());
        ui.output("\nPrize toy issued\n");
        ui.pressEnterToContinue();
    }
}
