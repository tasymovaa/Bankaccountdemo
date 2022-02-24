package Bankaccountdemo;
import java.sql.Driver;
import java.sql.DriverManager;

public class DataBase {
    private static final String dbClassNAme= "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/bank_account_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static java.sql.Connection connection() throws Exception{
        Class.forName(dbClassNAme);
        return DriverManager.getConnection(CONNECTION, USER, PASSWORD);
    }
}

