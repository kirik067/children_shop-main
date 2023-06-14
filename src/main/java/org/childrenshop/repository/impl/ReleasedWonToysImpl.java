package org.childrenshop.repository.impl;

import org.childrenshop.config.AppConfig;
import org.childrenshop.model.Toy;
import org.childrenshop.repository.ReleasedWonToys;
import org.childrenshop.utils.FileUtils;


public class ReleasedWonToysImpl implements ReleasedWonToys {

    @Override
    public void add(Object entity) {
        FileUtils.writeFile(((Toy) entity).toString() + "\n", AppConfig.getProperty("file.released_won_toys"), true);
    }
}

