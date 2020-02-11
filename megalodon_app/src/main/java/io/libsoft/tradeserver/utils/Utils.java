package io.libsoft.tradeserver.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class.getName());

    public static Properties fetchProperties() {
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:platform.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }

    public static Properties fetchProperties(String filePath) {
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile(filePath);
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }

    public static String getTelegramApiKey() {
        return Utils.fetchProperties("classpath:api-keys.properties").getProperty("telegram_api");
    }


}