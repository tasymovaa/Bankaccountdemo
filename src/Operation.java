package Bankaccountdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


//financial transactions
public class Operation {

    private String cardnumber;
    private String pincode;
    private int balance;

    Operation (String cardnumber, String pincode){
        this.cardnumber = cardnumber;
        this.pincode = pincode;
    }

    //show balance
    public Integer showBalance (String cardnumber){
        Scanner scanner = new Scanner(System.in);
        DataBase bs = new DataBase();
        try {
            Connection c = DataBase.connection();
            Statement stmt = c.createStatement();
            Statement stmt5 = c.createStatement();

            String sql5 = "SELECT * FROM Balance WHERE card_number = ' "+ cardnumber + "'";
            ResultSet rs5 = stmt5.executeQuery(sql5);
            while (rs5.next()){
                this.balance = rs5.getInt(2);
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return this.balance;
    }
//make deposit

    public void deposite (Integer amount , String cardnumber) {
//write the amount to the account

        try {
            Connection c = DataBase.connection();
            Statement stmt6 = c.createStatement();
            String sql6 = "UPDATE Balance SET balance= balance + '" +amount+ "' WHERE card_number = ' "+ cardnumber+" '";
            PreparedStatement preparedStatement = c.prepareStatement(sql6);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public void sendMoneyToOther (Integer amount_other, String number_other, String cardnumber) throws Exception {
        try{
            Connection c = DataBase.connection();
            //transfer to the account of another client

            Statement stmt8 = c.createStatement();
            String sql8 = "UPDATE Balance SET balance= balance + '" + amount_other + "' WHERE card_number = ' " + number_other + " '";
            stmt8.executeUpdate(sql8);
            //withdraw from the account of the current client

            Statement stmt9 = c.createStatement();
            String sql9 = "UPDATE Balance SET balance= balance + '" + amount_other + "' WHERE card_number = ' " + cardnumber + " '";
            stmt9.executeUpdate(sql9);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

