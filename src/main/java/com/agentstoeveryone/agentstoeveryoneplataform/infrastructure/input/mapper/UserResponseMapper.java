package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.mapper;

import com.agentstoeveryone.agentstoeveryoneplataform.domain.models.User;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.request.UserRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseMapper {

    public static User ToDomain(UserRequestDto userRequest){
        User entity = new User();
        entity.setUsername(userRequest.getUsername());
        entity.setName(userRequest.getName());
        entity.setMiddleName(userRequest.getMiddleName());
        entity.setLastname(userRequest.getLastname());
        entity.setSecondLastname(userRequest.getSecondLastname());
        entity.setPassword(userRequest.getPassword());
        return entity;
    }
}
