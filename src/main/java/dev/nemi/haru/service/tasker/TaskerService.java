package dev.nemi.haru.service.tasker;

import dev.nemi.haru.service.PaginatedDTO;
import dev.nemi.haru.service.SlicerDTO;

import java.util.List;

public interface TaskerService {
  long getMaxPages(int size);
  long getTaskCount();
  PaginatedDTO<TaskViewDTO> getTasksPaged(SlicerDTO dto);
  TaskViewDTO getTask(long id);
  int addTask(TaskAddDTO task);
  int updateTask(TaskUpdateDTO task);
  int deleteTask(long id);
}
