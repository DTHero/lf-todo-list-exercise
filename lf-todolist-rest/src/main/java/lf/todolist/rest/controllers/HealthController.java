package lf.todolist.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/ping")
    @ResponseStatus(code = HttpStatus.OK)
    public void ping() {
        // Health check endpoint
    }
}
