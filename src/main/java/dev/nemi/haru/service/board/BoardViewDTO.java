package dev.nemi.haru.service.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardViewDTO {
  long id;
  String title;
  String content;
  Instant added;
  Instant updated;
  @Nullable
  String userid;
}
