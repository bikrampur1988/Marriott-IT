package com.marriott.ui.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class SettingsReader {

    private static Document document;

    private static final String MODE =
            System.getProperty("mode", "local");

    private static final String ENVIRONMENT =
            System.getProperty("environment", "marriott-dev");


    static {
        loadSettingsFile();
    }


    private SettingsReader() {
        // Prevent creation of SettingsReader objects.
    }


    /*
     * =========================================================
     * LOAD SETTINGS FILE
     * =========================================================
     *
     * Examples:
     *
     * -Dmode=local
     *
     * loads:
     *
     * settings/local/marriott-dev-settings.xml
     *
     *
     * -Dmode=pipeline
     *
     * loads:
     *
     * settings/pipeline/marriott-dev-settings.xml
     */

    private static void loadSettingsFile() {

        try {

            String filePath =
                    "settings"
                            + File.separator
                            + MODE
                            + File.separator
                            + ENVIRONMENT
                            + "-settings.xml";


            File file = new File(filePath);


            if (!file.exists()) {

                throw new RuntimeException(
                        "Settings file not found at: "
                                + file.getAbsolutePath()
                );
            }


            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();


            document = builder.parse(file);

            document.getDocumentElement().normalize();


            System.out.println(
                    "Loaded settings file: "
                            + file.getAbsolutePath()
            );

            System.out.println(
                    "Execution mode: "
                            + MODE
            );


        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load settings XML file.",
                    e
            );
        }
    }


    /*
     * =========================================================
     * ROOT-LEVEL VALUE
     * =========================================================
     */

    private static String getValue(String tagName) {

        NodeList nodeList =
                document.getElementsByTagName(tagName);


        if (nodeList == null || nodeList.getLength() == 0) {

            throw new RuntimeException(
                    "Tag not found in settings XML: "
                            + tagName
            );
        }


        return nodeList
                .item(0)
                .getTextContent()
                .trim();
    }


    /*
     * =========================================================
     * SECTION-SPECIFIC VALUE
     * =========================================================
     *
     * This prevents problems when the same tag exists in
     * multiple sections.
     *
     * Example:
     *
     * <ui>
     *     <baseUrl>...</baseUrl>
     * </ui>
     *
     * <api>
     *     <baseUrl>...</baseUrl>
     * </api>
     */

    private static String getValue(
            String sectionName,
            String tagName
    ) {

        NodeList sectionList =
                document.getElementsByTagName(sectionName);


        if (sectionList == null || sectionList.getLength() == 0) {

            throw new RuntimeException(
                    "Section not found in settings XML: "
                            + sectionName
            );
        }


        Element section =
                (Element) sectionList.item(0);


        NodeList valueList =
                section.getElementsByTagName(tagName);


        if (valueList == null || valueList.getLength() == 0) {

            throw new RuntimeException(
                    "Tag '"
                            + tagName
                            + "' not found under section '"
                            + sectionName
                            + "'."
            );
        }


        return valueList
                .item(0)
                .getTextContent()
                .trim();
    }


    /*
     * =========================================================
     * EXECUTION MODE
     * =========================================================
     */

    public static String getMode() {

        return MODE;
    }


    public static boolean isPipelineMode() {

        return "pipeline".equalsIgnoreCase(MODE);
    }


    public static boolean isLocalMode() {

        return "local".equalsIgnoreCase(MODE);
    }


    /*
     * =========================================================
     * ENVIRONMENT
     * =========================================================
     */

    public static String getEnvironment() {

        return getValue("environment");
    }


    /*
     * =========================================================
     * UI SETTINGS
     * =========================================================
     */

    public static String getBrowser() {

        return getValue(
                "ui",
                "browser"
        );
    }


    public static boolean isHeadless() {

        return Boolean.parseBoolean(
                getValue(
                        "ui",
                        "headless"
                )
        );
    }


    public static boolean isMaximize() {

        return Boolean.parseBoolean(
                getValue(
                        "ui",
                        "maximize"
                )
        );
    }


    public static int getImplicitWait() {

        return Integer.parseInt(
                getValue(
                        "ui",
                        "implicitWait"
                )
        );
    }


    public static int getExplicitWait() {

        return Integer.parseInt(
                getValue(
                        "ui",
                        "explicitWait"
                )
        );
    }


    public static int getPageLoadTimeout() {

        return Integer.parseInt(
                getValue(
                        "ui",
                        "pageLoadTimeout"
                )
        );
    }


    public static String getBaseUrl() {

        return getValue(
                "ui",
                "baseUrl"
        );
    }


    /*
     * Selenium Grid URL.
     *
     * This should only be used for pipeline execution.
     */
    public static String getGridUrl() {

        return getValue(
                "ui",
                "gridUrl"
        );
    }


    /*
     * =========================================================
     * API SETTINGS
     * =========================================================
     */

    public static String getApiBaseUrl() {

        return getValue(
                "api",
                "baseUrl"
        );
    }


    public static int getConnectionTimeout() {

        return Integer.parseInt(
                getValue(
                        "api",
                        "connectionTimeout"
                )
        );
    }


    public static int getReadTimeout() {

        return Integer.parseInt(
                getValue(
                        "api",
                        "readTimeout"
                )
        );
    }
}