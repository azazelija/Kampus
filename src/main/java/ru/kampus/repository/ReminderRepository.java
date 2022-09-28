package ru.kampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.kampus.entity.ReminderEntity;

@Repository
public interface ReminderRepository extends JpaRepository<ReminderEntity, Long>, JpaSpecificationExecutor<ReminderEntity> {
}
