package dev.nemi.haru.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlicerDTO {

  public static final int DEFAULT_SIZE = 30;
  public static final String DEFAULT_SIZE_STR = DEFAULT_SIZE + "";
  public static final SlicerDTO DEFAULT = SlicerDTO.builder().build();

  @Builder.Default
  @Min(value = 1)
  @Positive
  private long page = 1;

  @Builder.Default
  @Min(value = 1)
  @Max(value = 100)
  @Positive
  private int size = DEFAULT_SIZE;

  public long getSkip() {
    return (page - 1) * size;
  }

  public String getSearchParams() {
    StringBuilder sb = new StringBuilder();
    if (page != 1) sb.append("&page=").append(page);
    if (size != DEFAULT_SIZE) sb.append("&size=").append(size);
    String search = sb.toString();
    return search.isEmpty() ? "" : search.replaceFirst("&", "?");
  }
}
