package lf.todolist.persistence.adapter.impl;

import lf.todolist.intf.adapters.UserAdapter;
import lf.todolist.intf.dto.User;
import lf.todolist.persistence.jpa.entity.UserEntity;
import lf.todolist.persistence.jpa.repository.UserRepository;
import lf.todolist.persistence.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAdapterImpl implements UserAdapter {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toUserDTO);
    }

    @Override
    public Optional<User> findByUserMobile(String countryCode, String mobile) {
        Optional<UserEntity> entity = userRepository.findByCountryCodeAndMobile(countryCode, mobile);
        return entity.map(userMapper::toUserDTO);
    }
}
