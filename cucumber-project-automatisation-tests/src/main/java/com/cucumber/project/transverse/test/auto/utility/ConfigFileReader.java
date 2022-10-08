package com.cucumber.project.transverse.test.auto.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigFileReader {
    
    private static final Logger LOGGER = Logger.getLogger(ConfigFileReader.class.getName());

    private static ConfigFileReader configFileReader = null;

    private static Properties properties;

    private ConfigFileReader() {
        properties = new Properties();
        addConfig("config/config.properties");
        addConfig("config/test.properties");
    }

    public static synchronized ConfigFileReader get() {

        if (configFileReader == null) {
            configFileReader = new ConfigFileReader();
        }

        return configFileReader;
    }

    public synchronized void addConfig(String filePath) {
        InputStream inputStream = this.getInputStream(filePath);
        if (inputStream != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader)) {
                try {
                    properties.load(reader);
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("/!\\ config.properties not found at " + filePath);
            }
        }  else {
            LOGGER.severe("Not Found : " + filePath);
        }
    }

    private InputStream getInputStream(String filePath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            try {
                inputStream = new FileInputStream(new File(filePath));
            } catch (FileNotFoundException e) {
                inputStream = null;
            }
        }
        return inputStream;
    }

    public String getPropertyValue(String property) {
        String propertyValue = properties.getProperty(property);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            throw new RuntimeException("/!\\ Property value not specified : " + property);
        }
    }
}