package com.dannydjrs.deserve.config;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties prop;

    public static void setup(InputStream input) throws IOException {
        prop = new Properties();
        prop.load(input);
    }

    public static String get(String property) {
        return prop.getProperty(property);
    }

    public static int getInt(String property) {
        return Integer.parseInt(get(property));
    }

    public static boolean getBoolean(String property) {
        return Boolean.parseBoolean(get(property));
    }
}
