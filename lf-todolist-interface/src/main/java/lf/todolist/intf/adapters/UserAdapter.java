package lf.todolist.intf.adapters;

import lf.todolist.intf.dto.User;

import java.util.Optional;

public interface UserAdapter {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserMobile(String countryCode, String mobile);

}
