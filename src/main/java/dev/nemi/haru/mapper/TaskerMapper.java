package dev.nemi.haru.mapper;

import dev.nemi.haru.TaskVO;
import dev.nemi.haru.service.tasker.TaskRequestDTO;

import java.util.List;

public interface TaskerMapper {
  List<TaskVO> getTasksFor(TaskRequestDTO requestDTO);
  long getTaskCountFor(TaskRequestDTO requestDTO);
  TaskVO getTask(long id);
  int addTask(TaskVO task);
  int updateTask(TaskVO task);
  int deleteTask(long id);
}
