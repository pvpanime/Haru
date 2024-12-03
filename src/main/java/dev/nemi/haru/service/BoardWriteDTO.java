package dev.nemi.haru.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteDTO {
  private String title;
  private String content;
  private @Nullable String userid;
}
