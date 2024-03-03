package com.example.data;

import java.util.*;

public class Notification extends Entity<UUID>
{ //that.getId()
    private String message;
    private Date sendDate;
    public Notification(String message, Date sendDate)
    {
        this.message = message;
        this.sendDate = sendDate;
    }

    public String getMessage() {return this.message;}
    public void setMessage(String message) {this.message = message;}

    public Date getSendDate() {return this.sendDate;}
    public void setSendDate(Date sendDate) {this.sendDate = sendDate;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getMessage(), getSendDate());
    }

}
