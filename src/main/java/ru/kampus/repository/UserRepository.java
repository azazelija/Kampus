package ru.kampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kampus.entity.UserEntity;

import java.nio.file.LinkOption;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);
}
