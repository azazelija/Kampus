package ru.kampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kampus.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    public RoleEntity findByRoleName(String roleName);
}
