package tianqiw.geolocationtracker.exception;

/**
 * Created by STuotuo.Wen on 2015/11/19.
 *
 * Exception to handling invalid input format
 */
public class CustomException extends Exception {
    private static String DEFAULT_NUMBER = "4126671285";
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public String fix() {
        return DEFAULT_NUMBER;
    }
}
