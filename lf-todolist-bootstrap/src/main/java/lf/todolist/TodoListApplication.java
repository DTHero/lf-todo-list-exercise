package lf.todolist;

import lf.todolist.persistence.configuration.TodoListPersistenceConfiguration;
import lf.todolist.rest.configuration.TodoListRestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        TodoListRestConfiguration.class,
        TodoListPersistenceConfiguration.class
})
public class TodoListApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }
}
