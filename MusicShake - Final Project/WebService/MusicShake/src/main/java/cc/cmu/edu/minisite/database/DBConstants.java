package cc.cmu.edu.minisite.database;

public interface DBConstants {
    String SQL_FILE = "query.txt";
    String DRIVER = "com.mysql.jdbc.Driver";
    String URL = "jdbc:mysql://127.0.0.1/test";
    String USERNAME = "root";
    String PASSWORD = "15319project";

    String SELECT_SONG_URL = "select * from songs where name like "
            + "\"%%%s%%\" or singer like \"%%%s%%\";";
    String SONG_BY_ID = "select * from songs where sid=%d;";
    String SIGNIN_QUERY = "select * from users where username=\"%s\" "
            + "and password=\"%s\";";
    String CHECK_EXIST_QUERY = "select * from users where username=\"%s\";";
    String SIGNUP_QUERY = "insert into users values(\"%s\", \"%s\");";
}

