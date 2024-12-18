package org.example.controller;

import org.example.model.Workflow;
import org.example.model.WorkflowTask;
import org.example.service.WorkflowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping
    public String listWorkflows(Model model) {
        List<Workflow> workflows = workflowService.getAllWorkflows();
        model.addAttribute("workflows", workflows);
        return "index";
    }

    @GetMapping("/create")
    public String showCreateWorkflowPage() {
        return "create";
    }

    @GetMapping("/createworkflows")
    public String createWorkflow(@RequestParam("name") String name) {
        workflowService.createWorkflow(name);
        return "redirect:/workflows";
    }

    @GetMapping("/{id}")
    public String viewWorkflowDetail(@PathVariable Long id, Model model) {
        Workflow workflow = workflowService.getWorkflowById(id);
        List<WorkflowTask> tasks = workflowService.getTasksByWorkflowId(id);
        model.addAttribute("workflow", workflow);
        model.addAttribute("tasks", tasks);
        return "workflow-detail";
    }

    @PostMapping("/tasks/{id}")
    public String completeTask(@PathVariable Long id) {
        workflowService.completeTask(id);
        return "redirect:/workflows";
    }
}

