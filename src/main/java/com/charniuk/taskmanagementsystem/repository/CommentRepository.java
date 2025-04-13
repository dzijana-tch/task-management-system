package com.charniuk.taskmanagementsystem.repository;

import com.charniuk.taskmanagementsystem.model.Comment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
