package dev.nemi.haru.controller;

import dev.nemi.haru.service.tasker.Tasker404;
import dev.nemi.haru.service.tasker.TaskerBadRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class Taurus {

  public ResponseEntity<String> a(int status, String mime) {
    return ResponseEntity.status(status).contentType(MediaType.parseMediaType(mime)).body("Bad Request");
  }

  @ExceptionHandler(value = { NoHandlerFoundException.class, TaskerBadRequest.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String badRequest() {
    return "view400";
  }


  @ExceptionHandler(value = { Tasker404.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String notFound() {
    return "view404";
  }

  @ResponseBody
  @ExceptionHandler(NumberFormatException.class)
  public String numberFormatAdvice(NumberFormatException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public String anyException(Exception ex) {
    Arrays.stream(ex.getStackTrace()).forEach(log::error);
    return ex.getMessage();
  }
}
