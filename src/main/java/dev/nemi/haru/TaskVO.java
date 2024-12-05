package dev.nemi.haru;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class TaskVO {
  long id;
  String title;
  String content;
  LocalDateTime start;
  LocalDateTime end;
  int status;
}
