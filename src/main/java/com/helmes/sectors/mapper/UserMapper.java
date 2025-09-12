package com.helmes.sectors.mapper;

import com.helmes.sectors.dto.UserDto;
import com.helmes.sectors.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToUser(UserDto userDto);
}