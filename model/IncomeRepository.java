package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import loginapp2.Income;


public class IncomeRepository {

    List<Income> incomes = new ArrayList<>();
    
    /**
    * This method reads all incomes from the database and stores them in a List.
    *
    * @return A List of Income objects
    */

    public List<Income> readIncomesUsers() {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1234");
            Statement myStat = myConn.createStatement();
            ResultSet myRs = myStat.executeQuery("select * from incomes");
            while (myRs.next()) {
                 Income newIncomes = new Income();
                 newIncomes.setId(myRs.getInt("id"));
                 newIncomes.setUserid(myRs.getString("Userid"));
                 newIncomes.setLocalDate(myRs.getDate("LocalDate").toLocalDate());
                 newIncomes.setIncomePrice(myRs.getFloat("incomePrice"));
                 newIncomes.setIncomeSubject(myRs.getString("incomeSubject"));
                 newIncomes.setTypeCost(myRs.getString("typeCost"));
                 newIncomes.setPriceCost(myRs.getFloat("priceCost"));
                 newIncomes.setCostSubject(myRs.getString("costSubject"));
                 incomes.add(newIncomes);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return incomes;
    }
     /**
      * This method adds a new income to the database.
      *
      * @param Userid The user's ID
      * @param localDate The date of the income
      * @param incomePrice The amount of the income
      * @param incomeSubject The subject of the income
      * @param typeCost The type of cost
      * @param priceCost The amount of the cost
      * @param costSubject The subject of the cost
      */
    public void writeRegisterUsers(String Userid, LocalDate localDate, float incomePrice, String incomeSubject, String typeCost,
            float priceCost, String costSubject) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1234");
            Statement myStat = myConn.createStatement();
            int maxId = 0;
            ResultSet myRs = myStat.executeQuery("SELECT MAX(id) FROM incomes;");
            if (myRs.next()) {
                maxId = myRs.getInt(1);
            }
            int id = maxId + 1;

            myStat.executeUpdate("INSERT INTO incomes (id ,Userid, LocalDate,"
                    + " incomePrice, incomeSubject, typeCost, priceCost, costSubject) values (" + id + ",'"
                    + Userid + "','" + localDate + "','" + incomePrice + "','"
                    + incomeSubject + "','" + typeCost + "','" + priceCost + "','" + costSubject + "');");

            System.out.println("user id incomer" + Userid);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
