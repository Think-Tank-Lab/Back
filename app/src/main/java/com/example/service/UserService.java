package com.example.service;

import com.example.data.User;
import com.example.data.validators.ValidationException;
import com.example.repository.UserDBRepository;

public class UserService{
    private final UserDBRepository userRepository;

    public UserService(UserDBRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String id, String firstName, String lastName, String email, String password)
    {
        User user = new User(id, firstName, lastName, email, password);
        if(userRepository.searchById(id) != null)
        {
            throw new ValidationException("\nExista deja acest User!\n");
        }
    }

    public void deleteUser(String id, String firstName, String lastName, String email, String password){
        User user = new User(id, firstName, lastName, email, password);
        if(userRepository.searchById(id) == null) {
            throw new ValidationException("\nAcest user nu exista!\n");
        }
        userRepository.delete(user);
    }

    public void updateUser(String id, String firstName, String lastName, String email, String password){
        User user = userRepository.searchById(id);
        if(user==null){
            throw new ValidationException("\nAcest user nu exista!\n");
        }
        User updatedUser=new User(id,firstName,lastName,email,password);
        userRepository.update(user,updatedUser);
    }

    /// TODO: DE ADAUGAT FUNCTIONALITATILE NECESARE APLICATIEI
}
