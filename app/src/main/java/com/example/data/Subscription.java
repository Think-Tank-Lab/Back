package com.example.data;

import java.util.*;

public class Subscription extends Entity<String>
{ //that.getId()
    private UUID user_id;
    private String subscriptionName;
    private Date startDate;
    private Date endDate;
    private String subscriptionType;
    private Float price;
    public Subscription(String id, String subscriptionName, Date startDate, Date endDate, String subscriptionType, Float price)
    {
        super(id);
        this.subscriptionName = subscriptionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subscriptionType = subscriptionType;
        this.price = price;
    }
    public UUID getUserID() {return this.user_id;}
    public void setUserID(UUID user_id) {this.user_id = user_id;}

    public String getSubscriptionName() {return this.subscriptionName;}
    public void setSubscriptionName(String subscriptionName) {this.subscriptionName = subscriptionName;}

    public Date getStartDate() {return this.startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}

    public Date getEndDate() {return this.endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}

    public String getSubscriptionType() {return this.subscriptionType;}
    public void setSubscriptionType(String subscriptionType) {this.subscriptionType = subscriptionType;}

    public Float getPrice() {return this.price;}
    public void setPrice(Float price) {this.price = price;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUserID(), getSubscriptionName(), getStartDate(), getEndDate(), getSubscriptionType(), getPrice());
    }


}
