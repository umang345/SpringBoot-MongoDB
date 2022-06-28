package com.umang345.springbootmongodb.service;

import com.umang345.springbootmongodb.exception.TodoCollectionException;
import com.umang345.springbootmongodb.model.TodoDTO;
import com.umang345.springbootmongodb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException,TodoCollectionException {
        Optional<TodoDTO> todoOptional = todoRepository.findByTodo(todoDTO.getTodo());
        if(todoOptional.isPresent())
        {
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }
        else {
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }

    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();
        if(todos.size()>0)
        {
             return todos;
        }
        else {
             return new ArrayList<>();
        }
    }
}
