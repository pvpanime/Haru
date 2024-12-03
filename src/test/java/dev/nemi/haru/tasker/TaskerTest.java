package dev.nemi.haru.tasker;

import dev.nemi.haru.service.tasker.TaskViewDTO;
import dev.nemi.haru.service.tasker.TaskerService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring.xml")
public class TaskerTest {

  @Autowired
  TaskerService taskerService;

  @Test
  public void getTaskTest() {
    List<TaskViewDTO> taskList = taskerService.getTasks();
    Assertions.assertNotNull(taskList);
    log.info(taskList);

    TaskViewDTO task = taskList.get(1);
    Assertions.assertNotNull(task);
    log.info(task);
  }

}
