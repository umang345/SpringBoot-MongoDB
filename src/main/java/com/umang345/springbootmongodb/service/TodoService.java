package com.umang345.springbootmongodb.service;

import com.umang345.springbootmongodb.exception.TodoCollectionException;
import com.umang345.springbootmongodb.model.TodoDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface TodoService
{
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException,TodoCollectionException;

    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;
}
