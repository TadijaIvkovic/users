package com.tadza.users.common;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);

    User toEntity(RegisterUserDto registerUserDto);

    void update(UpdateUserDto updateUserDto, @MappingTarget User user);

}
