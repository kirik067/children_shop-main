package org.childrenshop.view.menu;

import org.childrenshop.view.ui.UserInterface;
import org.childrenshop.view.ui.impl.UserInterfaceImpl;

import java.util.List;
import java.util.Optional;

public class Menu {
    private static final UserInterface ui = new UserInterfaceImpl<Integer>();

    public void processing(String menuHeader, List<MenuItem> menuItemList){
        while (true){
            ui.output(menuHeader);
            menuItemList.forEach(menuItem -> ui.output(menuItem.description()));

            Optional<Integer> itemOptional = ui.input("\nChoose your option: ", Integer::parseInt);

            if(itemOptional.isEmpty()){
                ui.output("\nInvalid! Try Again");
                ui.pressEnterToContinue();
                continue;
            }

            if(itemOptional.get() > menuItemList.size() || itemOptional.get() < 1){
                ui.output("\nInvalid! Try Again");
                ui.pressEnterToContinue();
            }
            try{
                menuItemList.get(itemOptional.get() - 1).executable().menuItemExecute();
            }catch (Exception e){
                ui.output("Cancelled");
            }

            if(itemOptional.get() == menuItemList.size()) break;
        }
    }
}
