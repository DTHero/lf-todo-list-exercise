package lf.todolist.intf.dto;

import lf.todolist.intf.enums.GenderEnum;
import lf.todolist.intf.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    UUID uuid;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    GenderEnum genderEnum;
    String countryCode;
    String mobile;
    String email;
    String address1;
    String address2;
    String address3;
    Boolean locked;
    LocalDateTime lastLogin;
    Integer numberOfRetries;
    RoleEnum role;
    List<TodoList> todoList;
}
