package com.charniuk.taskmanagementsystem.dto.request;

import com.charniuk.taskmanagementsystem.enums.TaskPriority;
import com.charniuk.taskmanagementsystem.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Запрос на создание задачи")
public class TaskRequest {

  @Schema(description = "Название задачи", example = "Разработка Системы Управления Задачами")
  @Size(min = 1, max = 100, message = "Название должно содержать от 5 до 100 символов")
  @NotBlank(message = "Название не может быть пустыми")
  private String title;

  @Schema(description = "Описание задачи", example = "Разработать систему, которая позволяет...")
  @Size(max = 1000, message = "Описание должно содержать до 1000 символов")
  private String description;

  @Schema(description = "Статус задачи", example = "PENDING")
  @NotBlank(message = "Статус не может быть пустым")
  private TaskStatus status;

  @Schema(description = "Приоритет задачи", example = "HIGH")
  @NotBlank(message = "Приоритет не может быть пустым")
  private TaskPriority priority;

  @Schema(description = "Исполнитель задачи", example = "e8d4747f-36b9-44ae-8e2e-541e54cec304")
  private UUID assignee;
}
