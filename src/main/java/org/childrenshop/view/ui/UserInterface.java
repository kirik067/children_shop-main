package org.childrenshop.view.ui;

import java.util.Optional;

public interface UserInterface <T> {
    Optional<T> input(String message, Parserable parser);
    void output(String message);
    void pressEnterToContinue();
}
