package com.umang345.springbootmongodb.controller;

import com.umang345.springbootmongodb.model.TodoDTO;
import com.umang345.springbootmongodb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController
{
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos()
    {
        List<TodoDTO> todos = todoRepository.findAll();
        if(todos.size()>0)
        {
             return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
        }
        else
        {
             return new ResponseEntity<>("No todos available",HttpStatus.NOT_FOUND);
        }
    }
}
