package com.charniuk.taskmanagementsystem.controller;

import com.charniuk.taskmanagementsystem.dto.request.CommentRequest;
import com.charniuk.taskmanagementsystem.dto.request.TaskRequest;
import com.charniuk.taskmanagementsystem.dto.response.CommentResponse;
import com.charniuk.taskmanagementsystem.dto.response.ErrorResponse;
import com.charniuk.taskmanagementsystem.dto.response.TaskResponse;
import com.charniuk.taskmanagementsystem.enums.TaskStatus;
import com.charniuk.taskmanagementsystem.model.Comment;
import com.charniuk.taskmanagementsystem.model.Task;
import com.charniuk.taskmanagementsystem.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Задачи")
public interface TaskController {

  /**
   * Создание задачи
   *
   * @param taskDto праметры для создания задачи
   * @return задача
   */
  @Operation(summary = "Создание задачи. Доступно администратору")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = TaskResponse.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @PostMapping
  ResponseEntity<TaskResponse> createTask(TaskRequest taskDto, User currentUser);

  /**
   * Редактирование задачи
   *
   * @param taskId      айди задачи
   * @param taskRequest     обновленная задача
   * @return задача
   */
  @Operation(summary = "Редактирование задачи. Доступно администратору")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Task.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @PutMapping("/{taskId}")
  ResponseEntity<TaskResponse> updateTask(UUID taskId, TaskRequest taskRequest);

  /**
   * Изменение статуса задачи
   *
   * @param taskId      айди задачи
   * @param taskStatus  новый статус задачи
   * @param currentUser текущий юзер
   * @return задача
   */
  @Operation(summary = "Изменение статуса задачи. Доступно исполнителю задачи и администратору")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Task.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @PutMapping("/status/{taskId}")
  ResponseEntity<TaskResponse> changeTaskStatus(UUID taskId, TaskStatus taskStatus, User currentUser);

  /**
   * Назначение исполнителя
   *
   * @param taskId      айди задачи
   * @param assigneeId  айди исполнителя
   * @return задача
   */
  @Operation(summary = "Назначение исполнителя. Доступно администратору")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Task.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @PutMapping("/assignee/{taskId}")
  ResponseEntity<TaskResponse> changeAssignee(UUID taskId, UUID assigneeId);

  /**
   * Просмотр задачи
   *
   * @param taskId айди задачи
   * @return задача
   */
  @Operation(summary = "Просмотр задачи")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Task.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @GetMapping("/{taskId}")
  ResponseEntity<TaskResponse> getTaskById(UUID taskId);

  /**
   * Удаление задачи
   *
   * @param taskId айди задачи
   */
  @Operation(summary = "Удаление задачи. Доступно администратору")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Task.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "404",
              description = "Not Found",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @DeleteMapping("/{taskId}")
  ResponseEntity<Void> deleteTask(UUID taskId);

  /**
   * Просмотр всех задач
   *
   * @return список задач
   */
  @Operation(summary = "Просмотр всех задач")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Task.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @GetMapping
  ResponseEntity<List<TaskResponse>> getAllTasks();

  /**
   * Оставить комментарий
   *
   * @return добавленный комментарий
   */
  @Operation(summary = "Добавление комментария. Доступно исполнителю задачи и администратору")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Comment.class))),
          @ApiResponse(
              responseCode = "403",
              description = "Forbidden",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  @PostMapping("/comment")
  ResponseEntity<CommentResponse> addComment(User currentUser, CommentRequest commentRequest);
}
