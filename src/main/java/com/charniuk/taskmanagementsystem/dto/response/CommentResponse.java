package com.charniuk.taskmanagementsystem.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CommentResponse(
    UUID commentId,
    String text,
    UserDto author,
    UUID taskId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
