package dev.nemi.haru.mapper;

import dev.nemi.haru.TaskVO;

import java.util.List;

public interface TaskerMapper {
  List<TaskVO> getTasks();
  TaskVO getTask(long id);
  int addTask(TaskVO task);
  int updateTask(TaskVO task);
  int deleteTask(long id);
}
