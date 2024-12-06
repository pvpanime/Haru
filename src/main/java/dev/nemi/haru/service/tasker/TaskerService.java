package dev.nemi.haru.service.tasker;

import dev.nemi.haru.service.PaginatedDTO;

public interface TaskerService {
  long getTaskCountFor(TaskRequestDTO request);
  PaginatedDTO<TaskViewDTO> getTasksFor(TaskRequestDTO dto);
  TaskViewDTO getTask(long id);
  int addTask(TaskAddDTO task);
  int updateTask(TaskUpdateDTO task);
  int deleteTask(long id);
}
