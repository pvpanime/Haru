package dev.nemi.haru.service.tasker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskViewDTO {
  private long id;
  String title;
  String content;
  LocalDateTime start;
  LocalDateTime end;
//  String end;
  int status;

  public boolean isExpired() {
    return status == 0 && end.isBefore(LocalDateTime.now());
  }
}
