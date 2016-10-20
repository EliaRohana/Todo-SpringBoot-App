package org.eliaroha.todo;

import org.eliaroha.todo.model.Todo;
import org.eliaroha.todo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Elia Rohana on 18/10/2016.
 */
@Component
public class AppInit implements CommandLineRunner {

    private Service service;

    @Autowired
    public AppInit(Service service) {
        this.service = service;
    }

    @Override
    public void run(String... strings) throws Exception {
        service.createDummyData();

        List<Todo> todoList = service.getTodoList();
        //        System.out.println(todo.getUser());
        todoList.forEach(System.out::println);
        System.out.println("Size: " + todoList.size());
    }
}
