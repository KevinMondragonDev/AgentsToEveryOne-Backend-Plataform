package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.repository;

import com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.output.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
