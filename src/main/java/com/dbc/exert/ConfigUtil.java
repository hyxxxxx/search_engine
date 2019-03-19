package com.dbc.exert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigUtil {

    private static Properties props = new Properties();

    static {
        ClassLoader loader = ConfigUtil.class.getClassLoader();
        InputStream ips = loader.getResourceAsStream("application.properties");
        try {
            props.load(new InputStreamReader(ips, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getValueStr(String key) {
        return props.getProperty(key);
    }

    public static int getValueInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }
}