package com.charniuk.taskmanagementsystem.mapper;

import com.charniuk.taskmanagementsystem.dto.request.CommentRequest;
import com.charniuk.taskmanagementsystem.dto.response.CommentResponse;
import com.charniuk.taskmanagementsystem.model.Comment;
import com.charniuk.taskmanagementsystem.model.Task;
import com.charniuk.taskmanagementsystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = ComponentModel.SPRING,
    uses = {UserMapper.class})
public interface CommentMapper {

  @Mapping(target = "author", source = "user")
  @Mapping(target = "task", source = "task")
  Comment toEntity(CommentRequest commentRequest, User user, Task task);

  @Mapping(target = "taskId", source = "comment.task.taskId")
  CommentResponse toResponse(Comment comment);
}
