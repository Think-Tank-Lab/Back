package com.example.service;

import com.example.data.Notification;
import com.example.data.validators.ValidationException;
import com.example.repository.NotificationDBRepository;

public class NotificationService{
    private final NotificationDBRepository notificationRepository;

    public NotificationService(NotificationDBRepository motificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotification(String id, String message, Date sendDate)
    {
        Notification notification = new Notification(id, message, sendDate);
        if(notificationRepository.searchById(id) != null)
        {
            throw new ValidationException("\nExista deja acest notification!\n");
        }
        notificationRepository.add(notification);
    }

    public void deleteNotification(String id, String message, Date sendDate){
        Notification notification = new Notification(id, message, sendDate);
        if(notificationRepository.searchById(id) == null) {
            throw new ValidationException("\nAcest notification nu exista!\n");
        }
        notificationRepository.delete(notification);
    }

    public void updateNotification(String id, String message, Date sendDate){
        Notification notification = notificationRepository.searchById(id);
        if(notification==null){
            throw new ValidationException("\nAcest notification nu exista!\n");
        }
        Notification updatedNotification=new Notification(id,message, sendDate);
        notificationRepository.update(notification,updatedNotification);
    }

    /// TODO: DE ADAUGAT FUNCTIONALITATILE NECESARE APLICATIEI
}
