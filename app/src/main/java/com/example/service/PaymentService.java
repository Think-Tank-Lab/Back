package com.example.service;

import com.example.data.Subscription;
import com.example.data.Payment;
import com.example.data.validators.ValidationException;
import com.example.repository.SubscriptionDBRepository;
import com.example.repository.PaymentDBRepository;

import java.util.Date;
import java.util.UUID;

public class PaymentService{
    private final PaymentDBRepository paymentRepository;
    private final SubscriptionDBRepository subscriptionRepository;

    public PaymentService(PaymentDBRepository paymentRepository, SubscriptionDBRepository subscriptionRepository) {

        this.paymentRepository = paymentRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void addPayment(String id, UUID subscription_id, Date paymentDate, Float amount, String status)
    {
        Payment payment = new Payment(id, paymentDate, amount, status);
        payment.setSubscription_id(subscription_id);
        UUID s_id = subscription_id;
        if(paymentRepository.searchById(id) != null)
        {
            throw new ValidationException("\nExista deja acest Payment!\n");
        }
        if(subscriptionRepository.searchById(String.valueOf(s_id)) == null){
            throw new ValidationException("\nNu exista acest Subscription!\n");
        }

        paymentRepository.add(payment);
    }

    public void deletePayment(String id, Date paymentDate, Float amount, String status){
        Payment payment = new Payment(id, paymentDate, amount, status);
        if(paymentRepository.searchById(id) == null) {
            throw new ValidationException("\nAcest payment nu exista!\n");
        }
        paymentRepository.delete(payment);
    }

    public void updatePayment(String id, Date paymentDate, Float amount, String status){
        Payment payment = paymentRepository.searchById(id);
        if(payment==null){
            throw new ValidationException("\nAcest payment nu exista!\n");
        }
        Payment updatedPayment=new Payment(id,paymentDate,amount, status);
        paymentRepository.update(payment,updatedPayment);
    }

    /// TODO: DE ADAUGAT FUNCTIONALITATILE NECESARE APLICATIEI
}
