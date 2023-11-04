package lf.todolist.persistence.adapter.impl;

import lf.todolist.intf.adapters.TodoListAdapter;
import lf.todolist.intf.dto.TodoList;
import lf.todolist.persistence.jpa.entity.TodoEntity;
import lf.todolist.persistence.jpa.entity.TodoItemEntity;
import lf.todolist.persistence.jpa.repository.TodoItemRepository;
import lf.todolist.persistence.jpa.repository.TodoRepository;
import lf.todolist.persistence.mapper.TodoItemMapper;
import lf.todolist.persistence.mapper.TodoMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoListAdapterImpl implements TodoListAdapter {

    TodoRepository todoRepository;
    TodoItemRepository todoItemRepository;
    TodoMapper todoMapper;
    TodoItemMapper todoItemMapper;

    @Override
    public List<TodoList> findAllByUserUuid(UUID userUuid) {
        return todoMapper.toTodoList(todoRepository.findAllByUserUuid(userUuid));
    }

    @Override
    public void createTodoList(UUID userUuid, List<TodoList> todoLists) {
        todoLists.forEach(todo -> {
            TodoEntity entity = todoMapper.toTodoEntity(todo);
            entity.setUserUuid(userUuid);
            TodoEntity newEntity = todoRepository.save(entity);
            List<TodoItemEntity> items = todoItemMapper.toTodoItemEntities(todo.getItems());
            items.forEach(item -> item.setTodoUuid(newEntity.getUuid()));
            todoItemRepository.saveAll(items);
        });
    }
}
