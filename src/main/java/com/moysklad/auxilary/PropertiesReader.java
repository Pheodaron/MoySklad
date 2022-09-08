package com.moysklad.auxilary;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private final Properties properties;

    public PropertiesReader(String propertyFileName) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("pom.properties");
        this.properties = new Properties();
        this.properties.load(is);
    }

   public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
   }
}
