package fr.epita.mob.services.spring;

import fr.epita.mob.services.exceptions.BadConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
    private static config instance;
    private Properties properties;

    private config(){
    }

    public static synchronized config getInstance() {
        if (instance == null){
            instance = new config();
            Properties properties = new Properties();
            String confFile = System.getProperty("conf.file");
            try {
                properties.load(new FileInputStream(confFile));
            } catch (IOException e) {
                throw new BadConfigurationException("File  not found : " + confFile);
            }
            instance.properties = properties;
        }
        return instance;
    }

    public String getDBUser(){
        return properties.getProperty("db.user");
    }

    public String getDBPassword(){
        return properties.getProperty("db.password");
    }

    public String getDBUrl(){
        return properties.getProperty("db.url");
    }
}
