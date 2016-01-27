package tianqiw.mortgageapp.util;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 *
 * this class is used to handle exceptions when input is not valid
 */
public class CustomException {
    private Logger logger;

        public CustomException() {
        this.logger = new Logger();
    }

    public int handle(int errno) {
        switch (errno) {
            case 1:
                logger.log("Purchase Price not correct");
                break;
            case 2:
                logger.log("Down Payment not correct");
                break;
            case 3:
                logger.log("Mortgage Term not correct");
                break;
            case 4:
                logger.log("Interest Rate not correct");
                break;
        }
        return 0;
    }
}
