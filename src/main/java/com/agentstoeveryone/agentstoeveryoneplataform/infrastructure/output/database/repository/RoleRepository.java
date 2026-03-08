package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository;

import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}

