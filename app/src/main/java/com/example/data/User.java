package com.example.data;

import java.util.*;

public class User extends Entity<UUID>
{ //that.getId()
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public User(String firstName, String lastName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.setId(UUID.randomUUID());
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email) {this.email = email;}

    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password;}

    //public Iterable<User> getSOMELISTIDK() {return SOMELISTIDK.values();}

    /*
    @Override
    public String toString()
    {
        return "User: " +
                "\nFirst Name: " + firstName  +
                ",\nLast Name: " + lastName  +
                ",\nEmail: " + email +
                '\n';
    }
    */

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getFirstName(), getLastName(), getEmail());
    }

}
