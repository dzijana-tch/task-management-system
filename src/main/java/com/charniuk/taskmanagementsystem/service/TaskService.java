package com.charniuk.taskmanagementsystem.service;

import com.charniuk.taskmanagementsystem.dto.request.CommentRequest;
import com.charniuk.taskmanagementsystem.dto.request.TaskRequest;
import com.charniuk.taskmanagementsystem.dto.response.CommentResponse;
import com.charniuk.taskmanagementsystem.dto.response.TaskResponse;
import com.charniuk.taskmanagementsystem.enums.TaskStatus;
import com.charniuk.taskmanagementsystem.model.User;
import java.util.List;
import java.util.UUID;

public interface TaskService {

  /**
   * Создание задачи
   *
   * @return созданная задача
   */
  TaskResponse createTask(TaskRequest taskRequest, User currentUser, User assignee);

  /**
   * Редактирование задачи
   *
   * @return обновленная задача
   */
  TaskResponse updateTask(UUID taskId, TaskRequest taskRequest);

  /**
   * Редактирование статуса задачи
   *
   * @return обновленная задача
   */
  TaskResponse changeTaskStatus(UUID taskId, TaskStatus status, User currentUser);

  /**
   * Назначение исполнителя
   *
   * @return обновленная задача
   */
  TaskResponse changeAssignee(UUID taskId, UUID assigneeId);

  /**
   * Получение информации о задаче по айди
   *
   * @return задача
   */
  TaskResponse getTaskInfo(UUID userId);

  /**
   * Удаление задачи по айди
   */
  void deleteTask(UUID taskId);

  /**
   * Получение всех задач
   *
   * @return список задач
   */
  List<TaskResponse> getAllTasks();

  /**
   * Добавить комментарий
   *
   * @return список задач
   */
  CommentResponse addComment(User currentUser, CommentRequest commentRequest);
}
