package com.dannydjrs.deserve.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author dannydjrs
 */
public class ConfigManager {
    private static final String PROPERTIES_POSTFIX = "properties";
    private HashMap<String, Properties> propertiesMap;

    public ConfigManager() {
        this.propertiesMap = new HashMap<>();
    }

    /**
     * 
     * @param configFile
     * @return
     * @throws IOException
     */
    private synchronized Properties load(String configFile) throws IOException {
        if (!this.propertiesMap.containsKey(configFile)) {
            Properties prop = new Properties();
            prop.load(new FileInputStream(String.format("%s.%s", configFile, ConfigManager.PROPERTIES_POSTFIX)));
            propertiesMap.put(configFile, prop);
        }

        return propertiesMap.get(configFile);
    }

    /**
     * 
     * @param configFile
     * @param property
     * @return
     */
    public String get(String configFile, String property) {
        try {
            return this.load(configFile).getProperty(property);
        } catch (IOException ex) {
            // TODO: LOG THE EXCEPTION
            return null;
        }
    }

    /**
     * 
     * @param configFile
     * @param property
     * @return
     */
    public int getInt(String configFile, String property) {
        return Integer.parseInt(this.get(configFile, property));
    }

    /**
     * 
     * @param configFile
     * @param property
     * @return
     */
    public boolean getBoolean(String configFile, String property) {
        return Boolean.parseBoolean(this.get(configFile, property));
    }
}
