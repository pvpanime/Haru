package dev.nemi.haru.controller;

import dev.nemi.haru.service.tasker.TaskUpdateDTO;
import dev.nemi.haru.service.tasker.TaskViewDTO;
import dev.nemi.haru.service.tasker.TaskerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskerController {

  private final TaskerService taskerService;

  @GetMapping("/task")
  public String tasker(Model model) {
    model.addAttribute("list", taskerService.getTasks());
    return "tasker/index";
  }

  @GetMapping("/task/view/{id}")
  public String taskerView(@PathVariable long id, Model model) {
    TaskViewDTO task = taskerService.getTask(id);
    if (task == null) {
      return "forward:view404";
    }
    model.addAttribute("task", task);
    return "tasker/view";
  }

  @GetMapping("/task/edit/{id}")
  public String taskerEdit(@PathVariable long id, Model model) {
    TaskViewDTO task = taskerService.getTask(id);
    if (task == null) {
      return "forward:view404";
    }
    model.addAttribute("task", task);
    return "tasker/edit";
  }

  @PostMapping("/task/edit")
  public String taskerEdit(TaskUpdateDTO task, Model model) {
    int result = taskerService.updateTask(task);
    return "redirect:/task/view/" + task.getId();
  }

}
