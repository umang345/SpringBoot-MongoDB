package com.umang345.springbootmongodb.service;

import com.umang345.springbootmongodb.exception.TodoCollectionException;
import com.umang345.springbootmongodb.model.TodoDTO;

import javax.validation.ConstraintViolationException;

public interface TodoService
{
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException,TodoCollectionException;
}
