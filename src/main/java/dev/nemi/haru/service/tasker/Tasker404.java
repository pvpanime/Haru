package dev.nemi.haru.service.tasker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Tasker404 extends RuntimeException {
  public Tasker404(String message) {
    super(message);
  }
}
