package org.example.service;

import org.example.mapper.WorkflowMapper;
import org.example.mapper.WorkflowTaskMapper;
import org.example.model.Workflow;
import org.example.model.WorkflowTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowService {
    private final WorkflowMapper workflowMapper;
    private final WorkflowTaskMapper taskMapper;

    public WorkflowService(WorkflowMapper workflowMapper, WorkflowTaskMapper taskMapper) {
        this.workflowMapper = workflowMapper;
        this.taskMapper = taskMapper;
    }

    public List<Workflow> getAllWorkflows() {
        return workflowMapper.selectList(null);
    }

    public void createWorkflow(String name) {
        Workflow workflow = new Workflow();
        workflow.setName(name);
        workflow.setStatus("待审批");
        workflowMapper.insert(workflow);
    }

    public List<WorkflowTask> getTasksByWorkflowId(Long workflowId) {
        return taskMapper.selectList(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<WorkflowTask>()
                .eq("workflow_id", workflowId));
    }

    public void completeTask(Long taskId) {
        WorkflowTask task = taskMapper.selectById(taskId);
        task.setStatus("已完成");
        taskMapper.updateById(task);
    }

    public Workflow getWorkflowById(Long workflowId){
        return workflowMapper.selectById(workflowId);
    }
}

