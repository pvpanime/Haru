package dev.nemi.haru.controller;

import dev.nemi.haru.service.PaginatedDTO;
import dev.nemi.haru.service.SlicerDTO;
import dev.nemi.haru.service.tasker.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@RequiredArgsConstructor
public class TaskerController {

  private final TaskerService taskerService;

  @GetMapping("/task")
//  public String index(Model model, @RequestParam(value = "pgSize", defaultValue = SlicerDTO.DEFAULT_SIZE_STR) int pgSize) {
  public String index(Model model, SlicerDTO slicer) {
//    SlicerDTO slicer = SlicerDTO.builder().size(pgSize).build();
    log.info("slicer={}", slicer);
    PaginatedDTO<TaskViewDTO> tasksSlice = taskerService.getTasksPaged(slicer);
    model.addAttribute("dto", tasksSlice);
    return "tasker/index";
  }

//  @GetMapping("/task/page/{pg}")
//  public String paginatedIndex(Model model, @PathVariable long pg, @RequestParam(value = "pgSize", defaultValue = SlicerDTO.DEFAULT_SIZE_STR) int pgSize) {
//    SlicerDTO slicer = SlicerDTO.builder().page(pg).size(pgSize).build();
//    PaginatedDTO<TaskViewDTO> tasksSlice = taskerService.getTasksPaged(slicer);
//    model.addAttribute("dto", tasksSlice);
//    return "tasker/index";
//  }

  @GetMapping("/task/view/{id}")
  public String taskerView(@PathVariable long id, Model model, SlicerDTO slicer) {
    log.info("slicer: {}", slicer);

    TaskViewDTO task = taskerService.getTask(id);
    if (task == null) {
      return "forward:view404";
    }
    model.addAttribute("task", task);
    return "tasker/view";
  }

  @GetMapping("/task/write")
  public String taskerWriteView() {
    return "tasker/write";
  }

  @PostMapping("/task/write")
  public String taskerWrite(@Valid TaskAddDTO dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
    log.info("end = {}", dto.getEnd());
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      return "redirect:/task/write";
    }
    log.info(dto);
    taskerService.addTask(dto);
    return "redirect:/task";
  }

  @GetMapping("/task/edit/{id}")
  public String taskerEditView(@PathVariable long id, Model model) {
    TaskViewDTO task = taskerService.getTask(id);
    if (task == null) {
      return "forward:view404";
    }
    model.addAttribute("task", task);
    return "tasker/edit";
  }

  @PostMapping("/task/edit")
  public String taskerEdit(@Valid TaskUpdateDTO task, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      return "redirect:/task/edit/" + task.getId();
    }
    int result = taskerService.updateTask(task);
    return "redirect:/task/view/" + task.getId();
  }

  @PostMapping("/task/delete/{id}")
  public String taskerDelete(@PathVariable long id, RedirectAttributes redirectAttributes) {
    taskerService.deleteTask(id);
    return "redirect:/task";
  }

}
