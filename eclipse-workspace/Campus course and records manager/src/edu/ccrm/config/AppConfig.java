package edu.ccrm.config;

public class AppConfig {
    private static AppConfig instance;
    private final String dataFolderPath = "data/";

    private AppConfig() {
        // Private constructor to prevent direct instantiation
        // Load configurations here, e.g., from a properties file
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getDataFolderPath() {
        return dataFolderPath;
    }
}
