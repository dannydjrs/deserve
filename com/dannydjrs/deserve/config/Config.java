package com.dannydjrs.deserve.config;

import java.util.Objects;

public class Config {
    public static boolean DEBUG;
    public static String ROOT;
    public static String SERVER_NAME;
    public static String SERVER_VERSION;

    public static boolean is_valid() {
        if (Objects.isNull(Config.DEBUG)) {
            return false;
        }

        if (Objects.isNull(Config.ROOT)) {
            return false;
        }

        if (Objects.isNull(Config.SERVER_NAME)) {
            return false;
        }

        if (Objects.isNull(Config.SERVER_VERSION)) {
            return false;
        }

        return true;
    }
}
