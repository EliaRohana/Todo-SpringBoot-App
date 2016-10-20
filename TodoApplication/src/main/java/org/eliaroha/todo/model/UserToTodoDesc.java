/*
 *  Copyright 2016 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: erohana
 *
 *  $Id: $
 */
package org.eliaroha.todo.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "userTodoDetail",
        classes = {
                @ConstructorResult(
                        targetClass = UserToTodoDesc.class,
                        columns = {
                                @ColumnResult(name = "name"),
                                @ColumnResult(name = "description"),
                                @ColumnResult(name = "summary")
                        }
                )
        }

)
public class UserToTodoDesc {

  private String userName;
  private String todoDesc;
  private String todoSummary;

  public UserToTodoDesc(String userName, String todoDesc, String todoSummary) {
    this.userName = userName;
    this.todoDesc = todoDesc;
    this.todoSummary = todoSummary;
  }

  public String getUserName() {
    return userName;
  }

  public String getTodoDesc() {
    return todoDesc;
  }

  public String getTodoSummary() {
    return todoSummary;
  }
}
