package config;

import java.io.InputStream;
import java.util.Properties;

public enum BlooogConfig {
    VALUES;
    private static final String CONFIG_PATH= "config.properties";
    private String mailPop3;
    private String mailSmtp;
    private String mailUser;
    private String mailPassword;
    private String errorMessage = null;
    private String url;
    private String smtpPort;

    BlooogConfig(){
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH)){
            if (inputStream == null){
                errorMessage = "Stream not open";
                return;
            }
            Properties prop = new Properties();
            prop.load(inputStream);
            this.mailUser = prop.getProperty("user");
            this.mailPassword = prop.getProperty("password");
            this.mailPop3 = prop.getProperty("pop3");
            this.mailSmtp = prop.getProperty("smtp");
            this.url = prop.getProperty("url");
            this.smtpPort = prop.getProperty("smtpPort");

        } catch (Exception e) {
            errorMessage = "File config not found!";
        }
    }

    public String getMailPop3() {
        return mailPop3;
    }

    public String getMailSmtp() {
        return mailSmtp;
    }

    public String getMailUser() {
        return mailUser;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getErrorMessage() {
        return errorMessage == null ? "No errors" : errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public String getSmtpPort() {
        return smtpPort;
    }
}
