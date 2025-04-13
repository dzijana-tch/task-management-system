package com.charniuk.taskmanagementsystem.dto.response;

import com.charniuk.taskmanagementsystem.enums.Role;
import java.util.UUID;
import lombok.Builder;

@Builder
public record UserDto(
    UUID userId,
    String email,
    String name,
    Role role
) {

}
