package lf.todolist.persistence.jpa.repository;

import lf.todolist.persistence.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByCountryCodeAndMobile(String countryCode, String mobile);
}
