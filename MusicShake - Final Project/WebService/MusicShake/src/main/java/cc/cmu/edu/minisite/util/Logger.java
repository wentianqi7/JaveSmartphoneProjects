package cc.cmu.edu.minisite.util;

public class Logger {
	private static final String[] TAGS = {"[Debug]", "[Error]"};
    /**
     * @param message
     * @param type
     *      0: debug
     *      1: error
     */
	public static void log(String message, int type) {
		switch (type) {
        case 0:
            System.out.println(TAGS[0] + message);
            break;
        case 1:
            System.err.println(TAGS[1] + message);
            break;
    }
	}
}
