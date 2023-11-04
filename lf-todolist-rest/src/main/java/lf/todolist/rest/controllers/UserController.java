package lf.todolist.rest.controllers;

import lf.todolist.application.service.identity.UserApplicationService;
import lf.todolist.intf.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserApplicationService userService;

    @GetMapping("/profile")
    public User getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userService.getProfileFor(authentication);
    }
}
