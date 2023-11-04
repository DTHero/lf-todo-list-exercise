package lf.todolist.persistence.mapper;

import lf.todolist.intf.dto.User;
import lf.todolist.persistence.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {TodoMapper.class})
public interface UserMapper {
    @Mapping(target = "todoList", ignore = true)
    User toUserDTO(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
