package org.childrenshop.config;

import org.childrenshop.controller.AppController;
import org.childrenshop.view.ui.UserInterface;
import org.childrenshop.view.ui.impl.UserInterfaceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfig {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private static final UserInterface ui = new UserInterfaceImpl();


    public static File getProperty(String key) {
        var property = new Properties();
        try(var inputStream = new FileInputStream(PATH_TO_PROPERTIES);){
            property.load(inputStream);

        } catch (IOException e){
            ui.output(e.toString());
        }

        return new File(property.getProperty(key));
    }
}
