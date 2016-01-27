package tianqiw.mortgageapp.util;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 * <p>
 * contains constants used by dbs
 */
public interface Constants {
    static String DATABASE = "MortgageRecords";

    static String[] FIELDS = {"purchase_price", "down_price", "mortgage_term",
            "interest_rate", "first_month", "first_year", "payment_month",
            "total_payment", "payoff_month", "payoff_year"};

    static String CREATE_TABLE = "CREATE TABLE myMortgage" +
            "(_id integer PRIMARY KEY AUTOINCREMENT, " +
            "purchase_price TEXT, " +
            "down_price TEXT, " +
            "mortgage_term TEXT, " +
            "interest_rate TEXT, " + "" +
            "first_month TEXT, " +
            "first_year TEXT, payment_month TEXT, " +
            "total_payment TEXT, payoff_month TEXT, " +
            "payoff_year TEXT, timestamp TEXT);";
}
