package lf.todolist.intf.dto;

import lf.todolist.intf.enums.LoginStatusEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginOut {
    LoginStatusEnum status;
    String token;

    public static LoginOut ofAuthenticationFailed() {
        return ofLoginStatus(LoginStatusEnum.FAILED, null);
    }

    public static LoginOut ofLoginSuccess(String token) {
        return ofLoginStatus(LoginStatusEnum.SUCCESS, token);
    }

    private static LoginOut ofLoginStatus(LoginStatusEnum status, String token) {
        return LoginOut.builder()
                .status(status)
                .token(token)
                .build();
    }
}
