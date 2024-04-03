package com.example.service;

import com.example.data.User;
import com.example.data.Subscription;
import com.example.data.validators.ValidationException;
import com.example.repository.UserDBRepository;
import com.example.repository.SubscriptionDBRepository;

import java.util.Date;
import java.util.UUID;

public class SubscriptionService{
    private final UserDBRepository userRepository;
    private final SubscriptionDBRepository subscriptionRepository;

    public SubscriptionService(UserDBRepository userRepository, SubscriptionDBRepository subscriptionRepository) {

        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void addSubscription(String id, String user_id, String subscriptionName, Date startDate, Date endDate, String subscriptionType, Float price)
    {
        Subscription subscription = new Subscription(id, subscriptionName, startDate, endDate, subscriptionType, price);
        if(userRepository.searchById(user_id) == null)
        {
            throw new ValidationException("\nAcest user nu exista!\n");
        }

        if(subscriptionRepository.searchById(id) != null){
            throw new ValidationException("\nExista deja acest subscription!\n");
        }
    }

    public void deleteSubscription(String id, String subscriptionName, Date startDate, Date endDate, String subscriptionType, Float price){
        Subscription subscription = new Subscription(id, subscriptionName, startDate, endDate, subscriptionType, price);
        if(subscriptionRepository.searchById(id) == null) {
            throw new ValidationException("\nAcest subscription nu exista!\n");
        }
        subscriptionRepository.delete(subscription);
    }

    public void updateSubscription(String id, String subscriptionName, Date startDate, Date endDate, String subscriptionType, Float price){
        Subscription subscription = subscriptionRepository.searchById(id);
        if(subscription==null){
            throw new ValidationException("\nAcest subscription nu exista!\n");
        }
        Subscription updatedSubscription=new Subscription(id,subscriptionName,startDate,endDate,subscriptionType, price);
        subscriptionRepository.update(subscription,updatedSubscription);
    }

    /// TODO: DE ADAUGAT FUNCTIONALITATILE NECESARE APLICATIEI
}
