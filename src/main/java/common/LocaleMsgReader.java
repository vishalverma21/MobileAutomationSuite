package common;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LocaleMsgReader {

    private static String language = ConfigManager.getProperty("Language");
    private static String country = ConfigManager.getProperty("Country");
    private static ResourceBundle resourceBundle = null;
    private static Locale locale = null;
    private static InputStreamReader stream = null;

    public static String getString(String value) throws UnsupportedEncodingException {
        // get locale object
        locale = new Locale(language);

        // get resource bundle
        resourceBundle = PropertyResourceBundle.getBundle("LocaleTest", locale);

        // get the message
        String message = resourceBundle.getString(value).trim();

        return new String(message.getBytes("ISO-8859-1"), "UTF-8");
    }

    public static String getCountry(String value) throws UnsupportedEncodingException {
        // get locale object
        locale = new Locale(country);

        // get resource bundle
        resourceBundle = PropertyResourceBundle.getBundle("CountryTest", locale);

        // get the message
        String message = resourceBundle.getString(value).trim();

        return new String(message.getBytes("ISO-8859-1"), "UTF-8");
    }

}
