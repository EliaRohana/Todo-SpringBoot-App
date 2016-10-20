/*
 *  Copyright 2016 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: erohana
 *
 *  $Id: $
 */
package org.eliaroha.todo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@NamedQuery(name = "userTodoDetailQuery",
//query = "select u.name,t.description, t.summary from t_user u join todo t on u.id = t.user_id")
@Entity
@Table(name = "t_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<Todo> todoList = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean addTodo(Todo todo) {
    return todoList.add(todo);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
