package Bankaccountdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Account {
    private String firstName;
    private String lastName;
    private Integer balance;

    Account (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // registers a new user

    public Boolean register (Account a) throws Exception{
        //do registration

        try{
            DataBase bd = new DataBase();
            Connection c = DataBase.connection();
            Statement stmt = c.createStatement();
            String sql = " INSERT INTO Account VALUES (null,'"+this.firstName+"','"+this.lastName+"') ";
            stmt.executeUpdate(sql);

            //last id
            Statement stmt2= c.createStatement();
            String sql2 = "SELECT LAST_iNSERT_ID()";
            ResultSet rs2 =stmt.executeQuery(sql2);

            int last_account_id=0;
            while (rs2.next()) {
                last_account_id= rs2.getInt(1);
            }

            String cardNumber= this.generateCardNumber();
            String code = this.generatePincode();

            //add card number and pin code
            Statement stmt3= c.createStatement();
            String sql3= " INSERT INTO card VALUES (null, '"+last_account_id+"' , '"+cardNumber+"', '"+code+"') ";

            stmt3.executeUpdate(sql3);

            //create balance sheet
            Statement stmt4= c.createStatement();
            String sql4 = "INSERT INTO Balance VALUES ( '"+cardNumber+"')";
            stmt4.executeUpdate(sql4);

            c.close();
            System.out.println("\n\n Account was successfully created.\n");
            System.out.println("Card number: " + cardNumber);
            System.out.println("Pincode: " + code);

            return true;

        }
        catch (Exception e) {
            System.out.println(e);
            return false;

        }
    }


    //generates card number
    public String generateCardNumber () {

        int length = 8;
        String passwordSet = "1234567890";
        char [] cardNumber = new char [length];
        for ( int i=0; i<length; i++) {
            int rand = (int) ( Math.random()* passwordSet.length());
            cardNumber[i] = passwordSet.charAt(rand);
        }

        return new String(cardNumber);
    }

    //generates pincode
    public String generatePincode () {

        int length = 4;
        String passwordSet = "1234567890";
        char[] cardNumber = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            cardNumber[i] = passwordSet.charAt(rand);
        }

        return new String(cardNumber);
    }

}

