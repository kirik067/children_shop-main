package org.childrenshop.view.menu.impl;

import org.childrenshop.view.menu.GeneralMenu;
import org.childrenshop.view.menu.Menu;

public class GeneralMenuImpl  extends Menu implements GeneralMenu {
    public void processing() {
        super.processing(header, generalMenuItems);
    }
}
