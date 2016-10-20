package org.eliaroha.todo.api;

import org.eliaroha.todo.model.Todo;
import org.eliaroha.todo.model.User;
import org.eliaroha.todo.repos.TodoRepository;
import org.eliaroha.todo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Elia Rohana on 19/10/2016.
 */
@RestController
@RequestMapping(value = "/{userId}/tasks",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TodoRestController  {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Autowired
    public TodoRestController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> findAllTodos(){return todoRepository.findAll();}


    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> findAllTodosByUser(@PathVariable long userId){
        validateUser(userId);
        return todoRepository.findByUser_Id(userId);
     }

     @RequestMapping(method = RequestMethod.POST)
     public ResponseEntity<?> addTodo(@PathVariable long userId, @RequestBody Todo todo){
         User user = validateUser(userId);
         todo.setUser(user);
         todoRepository.save(todo);
         return new ResponseEntity<>(HttpStatus.CREATED);
     }

     @RequestMapping(method = RequestMethod.DELETE)
     public ResponseEntity<?> delete(@PathVariable long userId, @RequestBody long todoId){
         todoRepository.delete((int) todoId);
         return new ResponseEntity<>(HttpStatus.OK);
     }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable long userId, @RequestBody Todo todo){
        Todo savedTodo = todoRepository.save(todo);
        if(savedTodo == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }

    private User validateUser(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        if(user == null)
            throw new UserNotFoundException(userId);
        return user;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(long userId) {
            super("could not find user '" + userId + "'.");
        }
    }
}
