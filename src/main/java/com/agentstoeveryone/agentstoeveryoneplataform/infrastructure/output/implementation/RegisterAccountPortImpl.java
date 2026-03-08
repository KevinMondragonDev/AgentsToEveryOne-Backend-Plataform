package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.implementation;

import com.agentstoeveryone.agentstoeveryoneplataform.domain.models.User;
import com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output.AssignRolePort;
import com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output.RegisterAccountPort;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.UserEntity;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAccountPortImpl implements RegisterAccountPort {

    private final UserRepository userRepository;
    private final AssignRolePort assignRolePort;


    public String register(User user) throws Exception {
        UserEntity userSave = getUserEntity(user);

        UserEntity savedUser = userRepository.save(userSave);

        // Asignar rol por defecto después de crear el usuario
        try {
            assignRolePort.assignRole(savedUser.getId(), 2L, "System"); // Role ID 2 = USER
        } catch (Exception e) {
            // Log error but don't fail the registration
            System.err.println("Error assigning default role: " + e.getMessage());
        }

        return "Usuario '" + savedUser.getUsername() + "' registrado exitosamente con ID: " + savedUser.getId();
    }

    private static UserEntity getUserEntity(User user) {
        UserEntity userSave = new UserEntity();
        userSave.setName(user.getName().trim());
        userSave.setUsername(user.getUsername().trim());
        userSave.setMiddleName(user.getMiddleName() != null ? user.getMiddleName().trim() : "");
        userSave.setPasswordHash(user.getPassword());
        userSave.setLastName(user.getLastname());
        userSave.setSecondLastName(user.getSecondLastname() != null ? user.getSecondLastname().trim() : "");
        userSave.setCreatedBy("System");
        userSave.setUpdatedBy("System");
        userSave.setActive(true); // Asegurar que el usuario está activo por defecto
        return userSave;
    }
}
