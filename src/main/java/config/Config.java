package config;

import java.io.InputStream;
import java.util.Properties;

final public class Config {
    public enum Mail{
        POP3,
        SMTP,
        USER,
        PASSWORD,
        SMTP_PORT;

        @Override
        public String toString() {
            return PropertiesFile.FILE.get().getProperty(name());
        }
    }

    public enum Server{
        URL;

        public String get(){
            return PropertiesFile.FILE.get().getProperty(name());
        }

    }

    private enum PropertiesFile {
        FILE;

        private static final String CONFIG_PATH= "config.properties";

        private Properties prop;

        PropertiesFile(){
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH)){
                if (inputStream == null){
                    return;
                }
                prop = new Properties();
                prop.load(inputStream);
            } catch (Exception e) {
                System.err.println("Can't load configuration file!!!");
            }
        }

        public Properties get(){
            return prop;
        }
    }
}
