package dev.nemi.haru.tasker;

import dev.nemi.haru.service.tasker.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring.xml")
public class TaskerTest {

  @Autowired
  TaskerService taskerService;

  @Test
  public void writeTaskTest() {
    taskerService.addTask(
      TaskAddDTO.builder()
        .title("Reunite the kingdom")
        .content("There's no time to wait! Let's do this!")
        .end(LocalDateTime.now().plusDays(3))
        .build()
    );
  }

  @Test
  public void editTaskTest() {
    taskerService.updateTask(TaskUpdateDTO.builder()
      .id(5)
      .title("Reunite the kingdom")
      .content("There's no time to wait!")
      .end(LocalDateTime.now().plusDays(3))
      .build()
    );
  }

  @Test
  public void paginationTest() {
//    PaginatedDTO<TaskViewDTO> ls = taskerService.getTasksFor(SlicerDTO.builder().page(1).size(10).build());
//    log.info(ls);
  }

}
