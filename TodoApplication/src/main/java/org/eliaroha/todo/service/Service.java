/*
 *  Copyright 2016 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: erohana
 *
 *  $Id: $
 */
package org.eliaroha.todo.service;

import org.eliaroha.todo.model.Todo;
import org.eliaroha.todo.model.User;
import org.eliaroha.todo.repos.TodoRepository;
import org.eliaroha.todo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Service {

  private TodoRepository todoRepository;

  private UserRepository userRepository;

  @Autowired
  public Service(TodoRepository todoRepository, UserRepository userRepository) {
    this.todoRepository = todoRepository;
    this.userRepository = userRepository;
  }

  public List<Todo> getTodoList(){
    ArrayList<Todo> todos = new ArrayList<>();
    todoRepository.findAll().iterator().forEachRemaining(todo->todos.add(todo));
    return todos;
  }

  public void save(Todo todo){
    todoRepository.save(todo);
  }

  public void save(User user){
    userRepository.save(user);
  }


  public void createDummyData(){

    User user = new User();
    user.setName("elia");


    for (int i = 0; i < 10; i++) {
      Todo todo = new Todo();
      todo.setUser(user);
      todo.setSummary("Todo #"+i);
      todo.setDescription("Todo #" +i + "Description");
//
//      todo = todoRepository.save(todo);
      user.addTodo(todo);
    }
    user = userRepository.save(user);


  }
}
