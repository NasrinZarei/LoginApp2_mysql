package loginapp2;

import java.io.Serializable;
 // This class represents a user in the system. It stores the user's unique identifier, name, phone number, email address, and password.
// Objects of this class can be serialized and deserialized to and from a stream of bytes. This is useful for storing user data in a database or transferring it between different systems.

public class User implements Serializable {

    private int id;
    private String nameandfamily;
    private String phone;
    private String email;
    private String password;
    public int count = 1;

    public User() {
    }

    public User(String nameandfamily, String phone, String email, String password) {
        id = count;
        count++;
        this.nameandfamily = nameandfamily;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public void setNameandfamily(String nameandfamily) {
        this.nameandfamily = nameandfamily;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameandfamily() {
        return nameandfamily;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPasword() {
        return password;
    }

}
