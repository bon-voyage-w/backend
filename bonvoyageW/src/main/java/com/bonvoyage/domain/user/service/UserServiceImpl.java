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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public List<UserDto> findUserList(Object o) {
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


    /*
findOrder() - 조회 유형의 service 메서드

addOrder() - 등록 유형의 service 메서드

modifyOrder() - 변경 유형의 service 메서드

removeOrder() - 삭제 유형의 service 메서드

saveOrder()
    * */
}
