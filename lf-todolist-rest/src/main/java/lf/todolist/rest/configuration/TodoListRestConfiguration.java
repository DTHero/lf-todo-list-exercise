package lf.todolist.rest.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lf.todolist.application.configuration.TodoListApplicationConfiguration;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@Import(TodoListApplicationConfiguration.class)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoListRestConfiguration {

    ObjectMapper mapper;

    @PostConstruct
    void configureObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        mapper.registerModule(simpleModule);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
