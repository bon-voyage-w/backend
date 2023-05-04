package com.bonvoyage.domain.user.service;


import com.bonvoyage.domain.user.dto.UserDto;
import com.bonvoyage.domain.user.entity.UserEntity;
import com.bonvoyage.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public List<UserDto> listMember(Object o) {
        List<UserEntity> userEntities=userRepository.findAll();
        List<UserDto> userDtos= new ArrayList<>();
        for (UserEntity user: userEntities) {
            UserDto userDto =UserDto.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .build();
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
