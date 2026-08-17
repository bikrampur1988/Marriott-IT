package com.marriott.ui.config;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SettingsReader {

    private static Document document;

    static {
        loadSettingsFile();
    }

    private SettingsReader() {
    }

    private static void loadSettingsFile() {
        try {
            String mode = System.getProperty("mode", "local");
            String environment = System.getProperty("environment", "marriott-dev");

            String filePath = "settings" + File.separator
                    + mode + File.separator
                    + environment + "-settings.xml";

            File file = new File(filePath);

            if (!file.exists()) {
                throw new RuntimeException("Settings file not found at: " + file.getAbsolutePath());
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            document = builder.parse(file);
            document.getDocumentElement().normalize();

            System.out.println("Loaded settings file: " + file.getAbsolutePath());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load settings XML file.", e);
        }
    }

    private static String getValue(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);

        if (nodeList == null || nodeList.getLength() == 0) {
            throw new RuntimeException("Tag not found in settings XML: " + tagName);
        }

        return nodeList.item(0).getTextContent().trim();
    }

    public static String getEnvironment() {
        return getValue("environment");
    }

    public static String getBrowser() {
        return getValue("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getValue("headless"));
    }

    public static boolean isMaximize() {
        return Boolean.parseBoolean(getValue("maximize"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getValue("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getValue("explicitWait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(getValue("pageLoadTimeout"));
    }

    public static String getBaseUrl() {
        return getValue("baseUrl");
    }

    public static String getApiBaseUrl() {
        return getValue("baseUrl");
    }

    public static int getConnectionTimeout() {
        return Integer.parseInt(getValue("connectionTimeout"));
    }

    public static int getReadTimeout() {
        return Integer.parseInt(getValue("readTimeout"));
    }
}