package com.umang345.springbootmongodb.controller;

import com.umang345.springbootmongodb.exception.TodoCollectionException;
import com.umang345.springbootmongodb.model.TodoDTO;
import com.umang345.springbootmongodb.repository.TodoRepository;
import com.umang345.springbootmongodb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController
{
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos()
    {
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos,
                todos.size()>0? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO)
    {
         try {
             todoService.createTodo(todoDTO);
             return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK);
         }
         catch (ConstraintViolationException e)
         {
             e.printStackTrace();
             return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
         }
         catch (TodoCollectionException e)
         {
              return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
         }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id)
    {
        try {
            return new ResponseEntity<>(todoService.getSingleTodo(id),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") String id, @RequestBody TodoDTO todo)
    {
        try{
            todoService.updateTodo(id,todo);
            return new ResponseEntity<>("Updated Todo with id "+id,HttpStatus.OK);
        }
        catch (ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (TodoCollectionException e)
        {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") String id)
    {
        try {
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted todo with id : "+id, HttpStatus.OK);
        }
        catch (TodoCollectionException e)
        {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}


















