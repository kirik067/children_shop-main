package org.childrenshop.view.ui.impl;

import org.childrenshop.view.ui.Parserable;
import org.childrenshop.view.ui.UserInterface;

import java.util.Optional;
import java.util.Scanner;

public class UserInterfaceImpl <T> implements UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public Optional<T> input(String message, Parserable parser) {
        while (true) {
            System.out.print(message);

            String line = scanner.nextLine();

            if (line.isEmpty()) {
                return Optional.empty();
            }

            try {
                T value = (T) parser.parse(line);
                return Optional.ofNullable(value);
            } catch (Exception e) {
                System.out.println("Invalid input data! Try Again");
            }
        }
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public void pressEnterToContinue(){
        System.out.println("\nPress Enter to continue");
        try{
            System.in.read();
        }
        catch(Exception e){

        }
    }
}
