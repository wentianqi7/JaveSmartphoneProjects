package tianqiw.scorestatistic.util;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 */
public interface Constants {
    static String DATABASE = "StudentRecords";

    static String[] FIELDS = {"sid", "q1", "q2", "q3", "q4", "q5"};

    static String CREATE_TABLE = "CREATE TABLE records" +
            "(sid INTEGER PRIMARY KEY," +
            "q1 REAL," +
            "q2 REAL," +
            "q3 REAL," +
            "q4 REAL," +
            "q5 REAL);";
}
