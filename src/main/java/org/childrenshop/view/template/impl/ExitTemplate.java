package org.childrenshop.view.template.impl;

import org.childrenshop.view.template.Template;

public class ExitTemplate implements Template {

    @Override
    public void output() {
        ui.output("\nApplication closed");
    }
}
