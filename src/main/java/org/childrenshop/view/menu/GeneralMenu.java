package org.childrenshop.view.menu;

import org.childrenshop.view.template.impl.*;

import java.util.Arrays;
import java.util.List;

public interface GeneralMenu {
    String header = "\nChildren Shop\n";
    List<MenuItem> generalMenuItems = Arrays.asList(
            new MenuItem[] {
                    new MenuItem("1. Add toy", () -> new AddToyTemplate().output()),
                    new MenuItem("2. Edit toy", () -> new EditToyTemplate().output()),
                    new MenuItem("3. Delete toy", () -> new DeleteToyTemplate().output()),
                    new MenuItem("4. View all toys", () -> new ViewAllToysTemplate().output()),
                    new MenuItem("5. Stock balance", () -> new StockbalanceTemplate().output()),
                    new MenuItem("6. Receipt of toy", () -> new ReceiptToyTemplate().output()),
                    new MenuItem("7. Issue of toy", () -> new IssueToysTemplate().output()),
                    new MenuItem("8. Toy lottery", () -> new ToyLotteryTemplate().output()),
                    new MenuItem("9. Give away a prize toy", () -> new GiveAwayToyTemplate().output()),
                    new MenuItem("10. Exit", () -> new ExitTemplate().output())
            }
    );
}