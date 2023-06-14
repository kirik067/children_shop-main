package org.childrenshop.utils;

import org.childrenshop.view.ui.UserInterface;
import org.childrenshop.view.ui.impl.UserInterfaceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class FileUtils {
    private static final UserInterface ui = new UserInterfaceImpl();

    public static ArrayList<String> readFile(File file){
        var data = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file));){
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e){
            ui.output(e.toString());
        }
        return data;
    }

    public static void writeFile(String data, File file, boolean append){
        try (FileWriter writer = new FileWriter(file, append);){
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            ui.output(e.toString());
        }
    }
}