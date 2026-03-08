package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository;

import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.UserRoleEntity;
import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {

    /**
     * Buscar todas las asociaciones de roles para un usuario específico
     *
     * SELECT * FROM user_roles WHERE user_id = ?
     *
     * EJEMPLO:
     * Si el usuario 1 tiene los roles ADMIN y USER:
     * Resultado: [UserRoleEntity(user=User1, role=ADMIN), UserRoleEntity(user=User1, role=USER)]
     */
    List<UserRoleEntity> findByUserId(Long userId);

    /**
     * Eliminar una asociación específica de usuario-rol
     *
     * DELETE FROM user_roles WHERE user_id = ? AND role_id = ?
     *
     * EJEMPLO:
     * Para revocar el rol ADMIN del usuario 1:
     * deleteByUserIdAndRoleId(1L, 1L) // elimina esa asociación
     */
    void deleteByUserIdAndRoleId(Long userId, Long roleId);
}

