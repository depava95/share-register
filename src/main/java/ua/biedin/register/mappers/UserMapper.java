package ua.biedin.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.biedin.register.controller.request.UserLoginRequest;
import ua.biedin.register.controller.request.UserTokenResponse;
import ua.biedin.register.controller.response.UserResponse;
import ua.biedin.register.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserLoginRequest toLoginDTO (User user);

    UserResponse toUserResponse (User user);

    User toUserFromLogin (UserLoginRequest userLoginRequest);

    User toUserFromResponse (UserResponse userResponse);

    UserTokenResponse toUserToken (User user);

}
