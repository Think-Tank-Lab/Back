package com.example.data.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}