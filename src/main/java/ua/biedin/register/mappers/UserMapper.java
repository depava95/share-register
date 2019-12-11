package ua.biedin.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.biedin.register.dto.UserLoginDTO;
import ua.biedin.register.dto.UserResponse;
import ua.biedin.register.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserLoginDTO toLoginDTO (User user);

    UserResponse toUserResponse (User user);

    User toUserFromLogin (UserLoginDTO userLoginDTO);

    User toUserFromResponse (UserResponse userResponse);

}
