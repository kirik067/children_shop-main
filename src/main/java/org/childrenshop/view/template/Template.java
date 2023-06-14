package org.childrenshop.view.template;

import org.childrenshop.service.LotteryService;
import org.childrenshop.service.StockService;
import org.childrenshop.service.ToyService;
import org.childrenshop.service.impl.LotteryServiceImpl;
import org.childrenshop.service.impl.StockServiceImpl;
import org.childrenshop.service.impl.ToyServiceImpl;
import org.childrenshop.view.ui.UserInterface;
import org.childrenshop.view.ui.impl.UserInterfaceImpl;

public interface Template<T> {
    ToyService toyService = new ToyServiceImpl();
    StockService stockService = new StockServiceImpl();
    LotteryService lotteryService = new LotteryServiceImpl();
    UserInterface ui = new UserInterfaceImpl();
    void output();
}
