package org.childrenshop.controller;

import org.childrenshop.view.menu.impl.GeneralMenuImpl;

public class AppController {
    public static void start(){
        new GeneralMenuImpl().processing();
    }
}
