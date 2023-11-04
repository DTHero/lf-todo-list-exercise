package lf.todolist.rest.controllers;

import lf.todolist.application.service.todo.TodoApplicationService;
import lf.todolist.intf.dto.TodoList;
import lf.todolist.intf.requests.TodoRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/todo")
@AllArgsConstructor
public class TodoController {

    private final TodoApplicationService todoApplicationService;

    @PostMapping("/new")
    public void createNewTodoList(@RequestBody TodoRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.todoApplicationService.createNewTodoList(authentication, request.getTodoLists());
    }

    @GetMapping("/todo-list")
    public List<TodoList> getTodoListOfUser(@RequestParam(value = "includeInactive", defaultValue = "true") boolean includeInactive) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getTodoList(this.todoApplicationService.getTodoList(authentication), includeInactive);
    }

    @GetMapping("/user/{userUuid}/todo-list")
    public List<TodoList> getTodoListOfUser(@PathVariable("userUuid") UUID userUuid, @RequestParam(value = "includeInactive", defaultValue = "true") boolean includeInactive) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getTodoList(this.todoApplicationService.adminGetTodoListOfUser(authentication, userUuid), includeInactive);
    }

    private List<TodoList> getTodoList(List<TodoList> rawList, boolean includeInactive) {
        if (!includeInactive) {
            return rawList.stream().filter(TodoList::getIsActive).collect(Collectors.toList());
        }
        return rawList;
    }
}
