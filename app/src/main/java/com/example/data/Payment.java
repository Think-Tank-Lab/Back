package com.example.data;

import java.util.*;

public class Payment extends Entity<UUID>
{ //that.getId()
    private UUID subscription_id;
    private Date paymentDate;
    private Float amount;
    private String status;
    public Payment(UUID subscription_id, Date paymentDate, Float amount, String status)
    {
        this.subscription_id = subscription_id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
        this.setId(UUID.randomUUID());
    }

    public UUID getSubscription_id() {return this.subscription_id;}
    public void setSubscription_id(UUID subscription_id) {this.subscription_id = subscription_id;}

    public Date getPaymentDate() {return this.paymentDate;}
    public void setPaymentDate(Date paymentDate) {this.paymentDate = paymentDate;}

    public Float getAmount() {return this.amount;}
    public void setAmount(Float amount) {this.amount = amount;}

    public String getStatus() {return this.status;}
    private void setStatus(String status) {this.status = status;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment that = (Payment) o;
        return id.equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getSubscription_id(), getPaymentDate(), getAmount(), getStatus());
    }


}
