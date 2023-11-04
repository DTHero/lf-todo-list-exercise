package lf.todolist.persistence.mapper;

import lf.todolist.intf.dto.TodoListItem;
import lf.todolist.persistence.jpa.entity.TodoItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TodoItemMapper {
    TodoListItem toTodoListItem(TodoItemEntity todoItemEntity);

    TodoItemEntity toTodoItemEntity(TodoListItem todoListItem);

    List<TodoListItem> toTodoListItems(List<TodoItemEntity> todoItemEntities);

    List<TodoItemEntity> toTodoItemEntities(List<TodoListItem> todoListItem);
}
