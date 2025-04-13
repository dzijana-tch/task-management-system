package com.charniuk.taskmanagementsystem.mapper;

import com.charniuk.taskmanagementsystem.dto.response.UserDto;
import com.charniuk.taskmanagementsystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = ComponentModel.SPRING)
public interface UserMapper {
  UserDto toDto(User user);
}
