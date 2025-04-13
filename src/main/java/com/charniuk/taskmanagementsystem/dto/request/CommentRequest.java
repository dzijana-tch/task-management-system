package com.charniuk.taskmanagementsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Запрос на создание комментария")
public class CommentRequest {

  @Schema(description = "Идентификатор задачи", example = "e8d4747f-36b9-44ae-8e2e-541e54cec304")
  @NotBlank(message = "Поле не может быть пустым")
  private UUID taskId;

  @Schema(description = "Текст комментария", example = "Написал контроллер...")
  @NotBlank(message = "Поле не может быть пустым")
  private String text;
}
