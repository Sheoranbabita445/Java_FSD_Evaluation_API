package com.springboot.libmanagement.service;


import com.springboot.libmanagement.dto.request.RegisterDto;
import com.springboot.libmanagement.mapper.UserMapper;
import com.springboot.libmanagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public User getUserObject(RegisterDto dto) {
        return UserMapper.convertDtoToEntity(dto);
    }

}
