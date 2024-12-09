package dev.nemi.haru.controller;

import dev.nemi.haru.service.PaginatedDTO;
import dev.nemi.haru.service.tasker.TaskRequestDTO;
import dev.nemi.haru.service.tasker.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@RequiredArgsConstructor
public class TaskerController {

  private final TaskerService taskerService;

  @GetMapping("/task")
  public String index(
    @Valid @ModelAttribute("requestDTO") TaskRequestDTO requestDTO,
    BindingResult bindingResult,
    Model model) {
    if (bindingResult.hasErrors()) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        log.warn(fieldError.toString());
      }
      return "redirect:/task";
    }

    PaginatedDTO<TaskViewDTO> tasksSlice = taskerService.getTasksFor(requestDTO);
    model.addAttribute("dto", tasksSlice);
    log.info(tasksSlice);
    return "tasker/index";
  }


  @GetMapping("/task/view/{id}")
  public String taskerView(
    @PathVariable long id,
    @Valid @ModelAttribute("requestDTO") TaskRequestDTO requestDTO,
    BindingResult bindingResult,
    Model model
  ) {
    if (bindingResult.hasErrors()) throw new TaskerBadRequest("BAD REQUEST");

    TaskViewDTO task = taskerService.getTask(id);
    if (task == null) throw new Tasker404("NOT FOUND");

    model.addAttribute("task", task);
    return "tasker/view";
  }


  @GetMapping("/task/write")
  public String taskerWriteView() {
    return "tasker/write";
  }

  @PostMapping("/task/write")
  public String taskerWrite(@Valid TaskAddDTO dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
      return "redirect:/task/write";
    }
    taskerService.addTask(dto);
    return "redirect:/task";
  }


  @GetMapping("/task/edit/{id}")
  public String taskerEditView(
    @PathVariable long id,
    Model model,
    @Valid @ModelAttribute("requestDTO") TaskRequestDTO requestDTO,
    BindingResult requestBR
  ) {
    TaskViewDTO task = taskerService.getTask(id);
    if (task == null) throw new Tasker404("TASK NOT FOUND!");

    if (requestBR.hasErrors()) {
      return "redirect:/task/edit/" + id;
    }
    model.addAttribute("task", task);
    return "tasker/edit";
  }

  public void injectAttributes(TaskRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
    if (requestDTO.getPage() != 1)
      redirectAttributes.addAttribute("page", requestDTO.getPage());
    if (requestDTO.getSize() != TaskRequestDTO.DEFAULT_SIZE)
      redirectAttributes.addAttribute("size", requestDTO.getSize());
    String[] searchFor = requestDTO.getSearchFor();
    if (searchFor != null) {
        redirectAttributes.addAttribute("searchFor", searchFor);
    }
    String search = requestDTO.getSearch();
    if (search != null && !search.isEmpty()) {
      redirectAttributes.addAttribute("search", search);
    }
    if (requestDTO.getMatchStatus() != null) {
      redirectAttributes.addAttribute("status", requestDTO.getMatchStatus());
    }
    if (requestDTO.getRangeStart() != null && requestDTO.getRangeEnd() != null) {
      redirectAttributes.addAttribute("rangeStart", requestDTO.getRangeStart());
      redirectAttributes.addAttribute("rangeEnd", requestDTO.getRangeEnd());
    }
  }

  @PostMapping("/task/edit")
  public String taskerEdit(
    TaskRequestDTO requestDTO,
    @Valid TaskUpdateDTO task,
    BindingResult taskBR,
    RedirectAttributes redirectAttributes
  ) {
    if (taskBR.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", taskBR.getAllErrors());
      return "redirect:/task/edit/" + task.getId() + requestDTO.usePage();
    }
    int result = taskerService.updateTask(task);
    String useSearch = requestDTO.usePage();
    if (useSearch != null && !useSearch.isEmpty()) useSearch = "?" + useSearch;
    return "redirect:/task/view/" + task.getId() + useSearch;
  }

  @PostMapping("/task/delete/{id}")
  public String taskerDelete(@PathVariable long id, TaskRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
    taskerService.deleteTask(id);
    String useSearch = requestDTO.usePage();
    if (useSearch != null && !useSearch.isEmpty()) useSearch = "?" + useSearch;
    return "redirect:/task" + useSearch;
  }

}
