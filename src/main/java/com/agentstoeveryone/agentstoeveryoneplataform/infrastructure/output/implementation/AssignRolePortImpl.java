package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.implementation;

import com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output.AssignRolePort;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.RoleEntity;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.UserEntity;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.UserRoleEntity;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository.UserRepository;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository.RoleRepository;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignRolePortImpl implements AssignRolePort {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    /**
     * LÓGICA DE ASIGNACIÓN DE ROL:
     *
     * 1. Buscar el usuario por ID
     * 2. Buscar el rol por ID
     * 3. Crear una asociación UserRoleEntity con:
     *    - user: el UserEntity encontrado
     *    - role: el RoleEntity encontrado
     *    - assignedAt: timestamp actual (auditoría)
     *    - assignedBy: quién está asignando el rol (auditoría)
     * 4. Guardar la asociación en la BD
     *
     * RESULTADO:
     * En la tabla user_roles se inserta un registro con:
     * user_id | role_id | assigned_at | assigned_by
     *    1    |    2    |  2026-03-08 |   admin
     */
    @Override
    public String assignRole(Long userId, Long roleId, String assignedBy) throws Exception {

        // Paso 1: Buscar el usuario
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("Usuario no encontrado: " + userId));

        // Paso 2: Buscar el rol
        RoleEntity role = roleRepository.findById(roleId)
            .orElseThrow(() -> new Exception("Rol no encontrado: " + roleId));

        // Paso 3: Crear la asociación UserRoleEntity
        // El constructor de conveniencia se encarga de:
        // - Asignar user y role
        // - Establecer assignedAt a la hora actual
        // - Crear la llave compuesta UserRoleId con (user.getId(), role.getId())
        UserRoleEntity userRole = new UserRoleEntity(user, role, assignedBy);

        // Paso 4: Guardar en la BD
        userRoleRepository.save(userRole);

        // Retornar confirmación
        return "Rol '" + role.getName() + "' asignado a usuario '" + user.getUsername() + "'";
    }

    /**
     * LÓGICA DE REVOCACIÓN DE ROL:
     *
     * 1. Buscar la asociación UserRoleEntity por (user_id, role_id)
     * 2. Eliminar la asociación
     *
     * RESULTADO:
     * Se elimina el registro de user_roles, el usuario deja de tener ese rol
     */
    @Override
    public String revokeRole(Long userId, Long roleId) throws Exception {

        // Buscar la asociación
        userRoleRepository.deleteByUserIdAndRoleId(userId, roleId);

        return "Rol revocado del usuario";
    }

    /**
     * OBTENER TODOS LOS ROLES DE UN USUARIO:
     *
     * 1. Buscar todas las asociaciones UserRoleEntity para ese usuario
     * 2. Extraer los nombres de roles
     * 3. Retornar lista de nombres
     *
     * EJEMPLO:
     * Usuario con ID 1 tiene:
     *   user_roles con role_id=1 (ADMIN)
     *   user_roles con role_id=2 (USER)
     * Resultado: ["ADMIN", "USER"]
     */
    @Override
    public List<String> getUserRoles(Long userId) {

        return userRoleRepository.findByUserId(userId)
            .stream()
            .map(userRole -> userRole.getRole().getName())
            .collect(Collectors.toList());
    }
}

