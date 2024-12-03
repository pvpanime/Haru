package dev.nemi.haru.service.tasker;

import java.util.List;

public interface TaskerService {
  List<TaskViewDTO> getTasks();
  TaskViewDTO getTask(long id);
  int addTask(TaskAddDTO task);
  int updateTask(TaskUpdateDTO task);
  int deleteTask(long id);
}
