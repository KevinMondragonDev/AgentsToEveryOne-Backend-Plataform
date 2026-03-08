package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.web;

import com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output.AssignRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ENDPOINT PARA ASIGNAR ROLES A USUARIOS
 *
 * EJEMPLOS DE USO:
 * ================
 *
 * 1. ASIGNAR UN ROL A UN USUARIO:
 *    POST /api/roles/assign
 *    Body: {
 *        "userId": 1,
 *        "roleId": 2,
 *        "assignedBy": "admin"
 *    }
 *
 *    Respuesta: "Rol 'USER' asignado a usuario 'john_doe'"
 *
 *
 * 2. REVOCAR UN ROL DE UN USUARIO:
 *    DELETE /api/roles/revoke/{userId}/{roleId}
 *
 *    Respuesta: "Rol revocado del usuario"
 *
 *
 * 3. OBTENER LOS ROLES DE UN USUARIO:
 *    GET /api/roles/user/{userId}
 *
 *    Respuesta: ["ADMIN", "USER", "MODERATOR"]
 */
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolesController {

    private final AssignRolePort assignRolePort;

    /**
     * Asigna un rol a un usuario
     *
     * FLUJO:
     * 1. Recibe userId (a quién asignamos), roleId (qué rol), assignedBy (quién asigna)
     * 2. Llama a assignRolePort.assignRole()
     * 3. assignRolePort busca User y Role en BD
     * 4. Crea UserRoleEntity con la asociación
     * 5. Guarda en tabla user_roles
     * 6. Retorna confirmación
     */
    @PostMapping("/assign")
    public ResponseEntity<String> assignRole(
            @RequestParam Long userId,
            @RequestParam Long roleId,
            @RequestParam String assignedBy) {
        try {
            String result = assignRolePort.assignRole(userId, roleId, assignedBy);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Revoca un rol de un usuario
     *
     * FLUJO:
     * 1. Recibe userId y roleId
     * 2. Llama a assignRolePort.revokeRole()
     * 3. assignRolePort busca la asociación en user_roles
     * 4. La elimina
     * 5. Retorna confirmación
     */
    @DeleteMapping("/revoke/{userId}/{roleId}")
    public ResponseEntity<String> revokeRole(
            @PathVariable Long userId,
            @PathVariable Long roleId) {
        try {
            String result = assignRolePort.revokeRole(userId, roleId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Obtiene todos los roles de un usuario
     *
     * FLUJO:
     * 1. Recibe userId
     * 2. Llama a assignRolePort.getUserRoles()
     * 3. assignRolePort busca todas las filas en user_roles con ese user_id
     * 4. Extrae los nombres de los roles asociados
     * 5. Retorna lista de nombres
     *
     * EJEMPLO:
     * Si usuario 1 tiene 3 roles:
     * - user_roles(user_id=1, role_id=1) → ADMIN
     * - user_roles(user_id=1, role_id=2) → USER
     * - user_roles(user_id=1, role_id=3) → MODERATOR
     *
     * Respuesta: ["ADMIN", "USER", "MODERATOR"]
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable Long userId) {
        List<String> roles = assignRolePort.getUserRoles(userId);
        return ResponseEntity.ok(roles);
    }
}

