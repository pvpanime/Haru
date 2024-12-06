package dev.nemi.haru.service.tasker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

  public static final int DEFAULT_SIZE = 30;
  public static final String DEFAULT_SIZE_STR = DEFAULT_SIZE + "";
  public static final TaskRequestDTO DEFAULT = TaskRequestDTO.builder().build();

  @Builder.Default
  @Min(value = 1)
  @Positive
  private long page = 1;

  @Builder.Default
  @Min(value = 1)
  @Max(value = 100)
  @Positive
  private int size = DEFAULT_SIZE;



  
  private String[] searchFor;

  @Builder.Default
  private String search = "";

  @Nullable
  private Integer status;
  private LocalDateTime rangeStart;
  private LocalDateTime rangeEnd;
  
  
  
  
  

  public long getSkip() {
    return (page - 1) * size;
  }

  public String getQuestion() {
    StringBuilder sb = new StringBuilder();
    if (page != 1) sb.append("&page=").append(page);
    if (size != DEFAULT_SIZE) sb.append("&size=").append(size);
    String search = sb.toString();
    return search.isEmpty() ? "" : search.replaceFirst("&", "?");
  }
}
