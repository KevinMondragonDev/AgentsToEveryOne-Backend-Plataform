package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.web;

import com.agentstoeveryone.agentstoeveryoneplataform.application.useCases.AccountManagementUseCase;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.mapper.UserResponseMapper;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final AccountManagementUseCase accountManagementUseCase;

    @Transactional
    @PostMapping("/signUp")
    public ResponseEntity<String> signUpUser(@RequestBody UserRequestDto userRequestDto) throws Exception {
            String result = accountManagementUseCase.register(UserResponseMapper.ToDomain(userRequestDto));
            return ResponseEntity.ok(result);
    }
}
