package dev.nemi.haru.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Value
@Builder
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class BoardVO {
  long boardId;
  String title;
  String content;
  @Nullable String userId;
  Instant added;
  Instant updated;
}
