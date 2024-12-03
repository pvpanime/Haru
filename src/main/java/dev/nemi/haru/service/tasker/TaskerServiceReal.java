package dev.nemi.haru.service.tasker;

import dev.nemi.haru.TaskVO;
import dev.nemi.haru.mapper.TaskerMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskerServiceReal implements TaskerService {

  private final TaskerMapper taskerMapper;
  private final ModelMapper modelMapper;

  @Override
  public List<TaskViewDTO> getTasks() {
    return taskerMapper.getTasks().stream().map(t -> modelMapper.map(t, TaskViewDTO.class)).collect(Collectors.toList());
  }

  @Override
  public TaskViewDTO getTask(long id) {
    return modelMapper.map(taskerMapper.getTask(id), TaskViewDTO.class);
  }

  @Override
  public int addTask(TaskAddDTO task) {
    return taskerMapper.addTask(modelMapper.map(task, TaskVO.class));
  }

  @Override
  public int updateTask(TaskUpdateDTO task) {
    return taskerMapper.updateTask(modelMapper.map(task, TaskVO.class));
  }

  @Override
  public int deleteTask(long id) {
    return taskerMapper.deleteTask(id);
  }
}
