package com.agentstoeveryone.agentstoeveryoneplataform.application.useCases;

import com.agentstoeveryone.agentstoeveryoneplataform.domain.models.User;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.request.UserRequestDto;

public interface AccountManagementUseCase {

    /**
     * Handles the creation of a new user account (Sign Up).
     */
    String register(User user) throws Exception;

    /*
    /**
     * Validates credentials and returns an access token or session data.
     LoginResponseDto Object login(LoginRequestDto loginRequest);
    */
    /**
     * Updates existing user profile information.
     */
    /*UserResponseDto Object  updateAccount(Long userId, UpdateAccountRequestDto updateRequest);

     */
}