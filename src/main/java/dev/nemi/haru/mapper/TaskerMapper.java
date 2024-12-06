package dev.nemi.haru.mapper;

import dev.nemi.haru.TaskVO;
import dev.nemi.haru.service.SlicerDTO;

import java.util.List;

public interface TaskerMapper {
//  List<TaskVO> getTasks();
  List<TaskVO> getTasksAt(SlicerDTO slicer);
  long getTaskCount();
  long getMaxPages(int size);
  TaskVO getTask(long id);
  int addTask(TaskVO task);
  int updateTask(TaskVO task);
  int deleteTask(long id);
}
