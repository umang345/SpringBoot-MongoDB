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

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> optionalTodo = todoRepository.findById(id);
        if(!optionalTodo.isPresent())
        {
             throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        else {
            return optionalTodo.get();
        }
    }

    @Override
    public void updateTodo(String id, TodoDTO todoDTO) throws TodoCollectionException {
        Optional<TodoDTO> todoWithId = todoRepository.findById(id);
        Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todoDTO.getTodo());

        if(todoWithId.isPresent())
        {
            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id))
            {
                 throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }

            TodoDTO todoToUpdate = todoWithId.get();
            todoToUpdate.setTodo(todoDTO.getTodo());
            todoToUpdate.setDescription(todoDTO.getDescription());
            todoToUpdate.setCompleted(todoDTO.getCompleted());
            todoDTO.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToUpdate);
        }
        else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }
}
