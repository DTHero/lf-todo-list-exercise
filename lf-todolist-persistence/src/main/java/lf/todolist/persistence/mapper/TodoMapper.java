package lf.todolist.persistence.mapper;

import lf.todolist.intf.dto.TodoList;
import lf.todolist.persistence.jpa.entity.TodoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {TodoItemMapper.class})
public interface TodoMapper {
    TodoList toTodo(TodoEntity todoEntity);

    TodoEntity toTodoEntity(TodoList todoList);

    List<TodoList> toTodoList(List<TodoEntity> todoEntity);

    List<TodoEntity> toTodoEntities(List<TodoList> todoLists);
}
