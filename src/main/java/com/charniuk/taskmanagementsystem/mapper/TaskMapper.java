package com.charniuk.taskmanagementsystem.mapper;

import com.charniuk.taskmanagementsystem.dto.request.TaskRequest;
import com.charniuk.taskmanagementsystem.dto.response.TaskResponse;
import com.charniuk.taskmanagementsystem.model.Task;
import com.charniuk.taskmanagementsystem.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = ComponentModel.SPRING,
    uses = {UserMapper.class})
public interface TaskMapper {

  @Mapping(target = "author", source = "currentUser")
  @Mapping(target = "assignee", source = "assignee")
  Task toEntity(TaskRequest taskRequest, User currentUser, User assignee);

  TaskResponse toResponse(Task task);

  List<TaskResponse> toResponse(List<Task> tasks);

}
