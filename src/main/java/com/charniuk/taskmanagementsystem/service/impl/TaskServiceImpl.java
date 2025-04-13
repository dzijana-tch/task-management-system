package com.charniuk.taskmanagementsystem.service.impl;

import com.charniuk.taskmanagementsystem.dto.request.CommentRequest;
import com.charniuk.taskmanagementsystem.dto.request.TaskRequest;
import com.charniuk.taskmanagementsystem.dto.response.CommentResponse;
import com.charniuk.taskmanagementsystem.dto.response.TaskResponse;
import com.charniuk.taskmanagementsystem.enums.Role;
import com.charniuk.taskmanagementsystem.enums.TaskStatus;
import com.charniuk.taskmanagementsystem.exceptions.NotFoundException;
import com.charniuk.taskmanagementsystem.mapper.CommentMapper;
import com.charniuk.taskmanagementsystem.mapper.TaskMapper;
import com.charniuk.taskmanagementsystem.model.Comment;
import com.charniuk.taskmanagementsystem.model.Task;
import com.charniuk.taskmanagementsystem.model.User;
import com.charniuk.taskmanagementsystem.repository.CommentRepository;
import com.charniuk.taskmanagementsystem.repository.TaskRepository;
import com.charniuk.taskmanagementsystem.service.TaskService;
import com.charniuk.taskmanagementsystem.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final CommentRepository commentRepository;
  private final UserService userService;
  private final TaskMapper taskMapper;
  private final CommentMapper commentMapper;

  @Override
  @Transactional
  public TaskResponse createTask(TaskRequest taskRequest, User currentUser, User assignee) {
    Task task = taskMapper.toEntity(taskRequest, currentUser, assignee);
    taskRepository.save(task);
    return taskMapper.toResponse(task);
  }

  @Override
  @Transactional
  public TaskResponse updateTask(UUID taskId, TaskRequest taskRequest) {

    Task existedTask = getTaskById(taskId);

    existedTask.setTitle(taskRequest.getTitle());
    existedTask.setDescription(taskRequest.getDescription());
    existedTask.setStatus(taskRequest.getStatus());
    existedTask.setPriority(taskRequest.getPriority());

    User assignee = userService.getByUserId(taskRequest.getAssignee());
    existedTask.setAssignee(assignee);

    return taskMapper.toResponse(existedTask);
  }

  @Override
  @Transactional
  public TaskResponse changeTaskStatus(UUID taskId, TaskStatus status, User currentUser) {
    Task task = getTaskById(taskId);

    if (isNotAssigneeOrAdmin(currentUser, task)) {
      throw new AccessDeniedException("Доступ запрещен");
    }

    task.setStatus(status);
    return taskMapper.toResponse(task);
  }

  @Override
  @Transactional
  public TaskResponse changeAssignee(UUID taskId, UUID assigneeId) {
    Task task = getTaskById(taskId);
    User user = userService.getByUserId(assigneeId);
    task.setAssignee(user);
    return taskMapper.toResponse(task);
  }

  @Override
  @Transactional(readOnly = true)
  public TaskResponse getTaskInfo(UUID userId) {
    Task task = getTaskById(userId);
    return taskMapper.toResponse(task);
  }

  /**
   * Получение задачи из репозитория по айди
   */
  private Task getTaskById(UUID taskId) {
    return taskRepository.findById(taskId)
        .orElseThrow(() -> new NotFoundException("Задача " + taskId + " не найдена"));
  }

  @Transactional
  @Override
  public void deleteTask(UUID taskId) {
    Task task = getTaskById(taskId);
    taskRepository.delete(task);
  }

  @Override
  public List<TaskResponse> getAllTasks() {
    List<Task> tasks = taskRepository.findAll();
    return taskMapper.toResponse(tasks);
  }

  @Transactional
  @Override
  public CommentResponse addComment(User currentUser, CommentRequest commentRequest) {

    Task task = getTaskById(commentRequest.getTaskId());

    if (isNotAssigneeOrAdmin(currentUser, task)) {
      throw new AccessDeniedException("Доступ запрещен");
    }

    Comment comment = commentMapper.toEntity(commentRequest, currentUser, task);
    commentRepository.save(comment);
    return commentMapper.toResponse(comment);
  }

  /**
   * Есть ли у юзера права на метод
   */
  private boolean isNotAssigneeOrAdmin(User user, Task task) {
    return !(task.getAssignee().getUserId().equals(user.getUserId())
        || user.getRole() == Role.ROLE_ADMIN);
  }
}
