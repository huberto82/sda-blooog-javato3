package config;

import java.io.InputStream;
import java.util.Properties;

public enum BlooogConfig {
    VALUES;
    private static final String CONFIG_PATH= "config.properties";
    String mailPop3;
    String mailSmtp;
    String mailUser;
    String mailPassword;
    String errorMessage = null;

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
}
