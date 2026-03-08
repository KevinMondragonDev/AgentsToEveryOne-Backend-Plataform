package com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output;

public interface AssignRolePort {

    /**
     * Asigna un rol a un usuario
     *
     * @param userId ID del usuario
     * @param roleId ID del rol a asignar
     * @param assignedBy Usuario que está asignando el rol (auditoría)
     * @return mensaje de confirmación
     * @throws Exception si el usuario o rol no existen
     */
    String assignRole(Long userId, Long roleId, String assignedBy) throws Exception;

    /**
     * Revoca un rol de un usuario
     *
     * @param userId ID del usuario
     * @param roleId ID del rol a revocar
     * @return mensaje de confirmación
     * @throws Exception si la asociación no existe
     */
    String revokeRole(Long userId, Long roleId) throws Exception;

    /**
     * Obtiene todos los roles asignados a un usuario
     *
     * @param userId ID del usuario
     * @return lista de roles del usuario
     */
    java.util.List<String> getUserRoles(Long userId);
}

