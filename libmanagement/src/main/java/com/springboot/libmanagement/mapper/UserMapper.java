package com.springboot.libmanagement.mapper;


import com.springboot.libmanagement.dto.request.RegisterDto;
import com.springboot.libmanagement.model.User;

public class UserMapper {
    public static User convertDtoToEntity(RegisterDto dto){
        User user = new User();
        user.setUsername(dto.username());
        user.setRole(dto.role());
        return user;
    }
}