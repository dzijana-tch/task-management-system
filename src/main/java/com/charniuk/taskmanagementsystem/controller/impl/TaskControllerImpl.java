package com.charniuk.taskmanagementsystem.controller.impl;

import com.charniuk.taskmanagementsystem.controller.TaskController;
import com.charniuk.taskmanagementsystem.dto.request.CommentRequest;
import com.charniuk.taskmanagementsystem.dto.request.TaskRequest;
import com.charniuk.taskmanagementsystem.dto.response.CommentResponse;
import com.charniuk.taskmanagementsystem.dto.response.TaskResponse;
import com.charniuk.taskmanagementsystem.enums.TaskStatus;
import com.charniuk.taskmanagementsystem.model.User;
import com.charniuk.taskmanagementsystem.service.TaskService;
import com.charniuk.taskmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

  private final TaskService taskService;
  private final UserService userService;

  @Override
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TaskResponse> createTask(
      @RequestBody @Valid TaskRequest taskRequest,
      @AuthenticationPrincipal User currentUser) {

    User assignee =
        taskRequest.getAssignee() == null ? null
            : userService.getByUserId(taskRequest.getAssignee());

    return ResponseEntity.ok(
        taskService.createTask(taskRequest, currentUser, assignee));
  }

  @Override
  @PutMapping("/{taskId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TaskResponse> updateTask(
      @PathVariable UUID taskId,
      @RequestBody @Valid TaskRequest taskRequest) {

    return ResponseEntity.ok(
        taskService.updateTask(taskId, taskRequest));
  }

  @Override
  @PutMapping("/status/{taskId}")
  public ResponseEntity<TaskResponse> changeTaskStatus(@PathVariable UUID taskId,
      @RequestParam TaskStatus taskStatus, @AuthenticationPrincipal User currentUser) {

    return ResponseEntity.ok(
        taskService.changeTaskStatus(taskId, taskStatus, currentUser));
  }

  @Override
  @PutMapping("/assignee/{taskId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TaskResponse> changeAssignee(@PathVariable UUID taskId,
      @RequestParam UUID assigneeId) {
    return ResponseEntity.ok(
        taskService.changeAssignee(taskId, assigneeId));
  }

  @Override
  @GetMapping("/{taskId}")
  public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID taskId) {
    return ResponseEntity.ok(
        taskService.getTaskInfo(taskId));
  }

  @Override
  @DeleteMapping("/{taskId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteTask(
      @PathVariable UUID taskId) {
    taskService.deleteTask(taskId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<List<TaskResponse>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }

  @Override
  @PostMapping("/comment")
  public ResponseEntity<CommentResponse> addComment(@AuthenticationPrincipal User currentUser,
      @RequestBody @Valid CommentRequest commentRequest) {

    return ResponseEntity.ok(
        taskService.addComment(currentUser, commentRequest));
  }
}
