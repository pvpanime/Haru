package dev.nemi.haru.service.tasker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddDTO {
  @NotEmpty private String title;
  @NotEmpty private String content;
  @NotNull @FutureOrPresent private LocalDateTime end;
  @Null private Integer finished;
}
