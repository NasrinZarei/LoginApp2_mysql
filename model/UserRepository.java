package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import loginapp2.User;

public class UserRepository {

    List<User> Users = new ArrayList<>();

    /**
     * This method reads all users from the database and stores them in a List.
     *
     * @return A List of User objects
     */
    public List<User> readRegisterUsers() {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1234");
            Statement myStat = myConn.createStatement();
            ResultSet myRs = myStat.executeQuery("select * from users");
            while (myRs.next()) {
                User newUser = new User();
                newUser.setId(myRs.getInt("id"));
                newUser.setNameandfamily(myRs.getString("nameandfamily"));
                newUser.setPhone(myRs.getString("phone"));
                newUser.setEmail(myRs.getString("email"));
                newUser.setPassword(myRs.getString("password"));

                Users.add(newUser);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return Users;
    }

    /**
     * This method adds a new user to the database.
     *
     * @param nameandfamily The user's name and family
     * @param phone The user's phone number
     * @param email The user's email address
     * @param password The user's password
     */

    public void writeRegisterUsers(String nameandfamily, String phone, String email, String password) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1234");
            Statement myStat = myConn.createStatement();
            int maxId = 0;
            ResultSet myRs = myStat.executeQuery("SELECT MAX(id) FROM users;");
            if (myRs.next()) {
                maxId = myRs.getInt(1);
            }

            int id = maxId + 1;

            myStat.executeUpdate("insert into users(id,nameandfamily,phone,email,password)values (" + id + ",'"
                    + nameandfamily + "','" + phone + "','" + email + "','" + password + "');");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
