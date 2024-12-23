package senla.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyUtil {

    private static final Properties PROPERTIES = new Properties();

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadFile(){
        try(InputStream stream = PropertyUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
                PROPERTIES.load(stream);
            } catch (IOException e) {
                throw new RuntimeException(ErrorMessages.PROPERTY_LOAD_ERROR.getDescription(), e);
            }
    }
    static {
        loadFile();
    }

    private PropertyUtil() {}
}

