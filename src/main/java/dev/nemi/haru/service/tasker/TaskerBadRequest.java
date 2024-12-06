package dev.nemi.haru.service.tasker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TaskerBadRequest extends RuntimeException {
  public TaskerBadRequest(String message) {
    super(message);
  }
}
