package com.charniuk.taskmanagementsystem.dto.response;

import com.charniuk.taskmanagementsystem.enums.TaskPriority;
import com.charniuk.taskmanagementsystem.enums.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TaskResponse(
    UUID taskId,
    String title,
    String description,
    TaskStatus status,
    TaskPriority priority,
    UserDto author,
    UserDto assignee,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
