package lf.todolist.persistence.configuration;

import lf.todolist.intf.adapters.TodoListAdapter;
import lf.todolist.intf.adapters.UserAdapter;
import lf.todolist.persistence.adapter.impl.TodoListAdapterImpl;
import lf.todolist.persistence.adapter.impl.UserAdapterImpl;
import lf.todolist.persistence.jpa.entity.UserEntity;
import lf.todolist.persistence.jpa.listener.TodoListSecurityAuditorAware;
import lf.todolist.persistence.jpa.repository.TodoItemRepository;
import lf.todolist.persistence.jpa.repository.TodoRepository;
import lf.todolist.persistence.jpa.repository.UserRepository;
import lf.todolist.persistence.mapper.TodoItemMapper;
import lf.todolist.persistence.mapper.TodoMapper;
import lf.todolist.persistence.mapper.UserMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EntityScan(basePackageClasses = UserEntity.class)
@EnableJpaRepositories(basePackages = "lf.todolist.persistence.jpa.repository")
public class TodoListPersistenceConfiguration {

    @Bean
    public AuditorAware<String> springSecurityAuditorAware() {
        return new TodoListSecurityAuditorAware();
    }

    @Bean
    public UserAdapter userAdapter(UserRepository userRepository, UserMapper userMapper) {
        return new UserAdapterImpl(userRepository, userMapper);
    }

    @Bean
    public TodoListAdapter todoListAdapter(TodoRepository todoRepository, TodoItemRepository todoItemRepository, TodoMapper todoMapper, TodoItemMapper todoItemMapper) {
        return new TodoListAdapterImpl(todoRepository, todoItemRepository, todoMapper, todoItemMapper);
    }
}
