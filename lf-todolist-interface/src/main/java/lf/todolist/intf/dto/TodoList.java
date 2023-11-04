package lf.todolist.intf.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoList {
    String title;
    String goal;
    LocalDate startDate;
    LocalDate endDate;
    List<TodoListItem> items;
    Boolean isActive;
}
