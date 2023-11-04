package lf.todolist.rest.controllers;

import lf.todolist.application.service.identity.AuthApplicationService;
import lf.todolist.intf.dto.LoginOut;
import lf.todolist.intf.requests.LoginEmailRequest;
import lf.todolist.intf.requests.LoginMobileRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    AuthApplicationService authApplicationService;

    @PostMapping("/email")
    public LoginOut loginWithEmail(@RequestBody LoginEmailRequest emailRequest) {
        return authApplicationService.loginWithEmail(emailRequest.getEmail(), emailRequest.getPassword());
    }

    @PostMapping
    @RequestMapping("/mobile")
    public LoginOut loginWithMobile(@RequestBody LoginMobileRequest mobileRequest) {
        return authApplicationService
                .loginWithPhoneNumber(mobileRequest.getCountryCode(), mobileRequest.getMobile(), mobileRequest.getPassword());
    }
}
