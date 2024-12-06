package dev.nemi.haru.service;

import dev.nemi.haru.service.tasker.TaskRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class PaginatedDTO<Tp> {

  private static final int PAGINATION_WINDOW_SIZE = 10;

  /** the current page (requested by client) */
  private long page;

  /** the number of tasks to show in a page. */
  private int size;
  private long total;

  private long start;
  private long end;

  private long last;

  private boolean prev;
  private boolean next;

  private List<Tp> list;

  @Builder(builderMethodName = "withAll")
  public PaginatedDTO(TaskRequestDTO requestDTO, List<Tp> ls, long total) {
    this.page = requestDTO.getPage();
    this.size = requestDTO.getSize();
    this.total = total;
    this.list = ls;
    this.last = (long) Math.ceil((double)total/size);

    if (PAGINATION_WINDOW_SIZE >= last) {
      this.start = 1;
      this.end = last;
    } else {
      int expandRight = (PAGINATION_WINDOW_SIZE / 2);
      int expandLeft = PAGINATION_WINDOW_SIZE - expandRight - 1;

      int pressureRight = page + expandRight > last ? (int) (page + expandRight - last) : 0;
      int pressureLeft = page - expandLeft < 1 ? (int) (expandLeft - page + 1) : 0;

      this.end = Math.min(page + expandRight + pressureLeft, last);

      this.start = Math.max(page - expandLeft - pressureRight, 1L);

      if (this.start <= 1 + 2) this.start = 1;
      if (this.end >= this.last - 2) this.end = this.last;

    }

    this.prev = this.start > 1;
    this.next = this.end * this.size < total;
  }

  public String usePage(long pg) {
    StringBuilder sb = new StringBuilder();
    if (pg > 1) sb.append("&page=").append(pg);
    if (size != TaskRequestDTO.DEFAULT_SIZE) sb.append("&size=").append(size);
    String s = sb.toString();
    return s.isEmpty() ? "" : s.replaceFirst("&", "?");

  }

  public String usePage() {
    return usePage(this.page);
  }
}
