package com.charniuk.taskmanagementsystem.repository;

import com.charniuk.taskmanagementsystem.model.Task;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
