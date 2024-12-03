package dev.nemi.haru;

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
  long id;
  String title;
  String content;
  Instant added;
  Instant updated;
  @Nullable String userid;
}
