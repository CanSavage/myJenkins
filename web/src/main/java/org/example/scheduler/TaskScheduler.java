package org.example.scheduler;

import org.example.mapper.WorkflowTaskMapper;
import org.example.model.WorkflowTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskScheduler {

    private final WorkflowTaskMapper taskRepository;

    public TaskScheduler(WorkflowTaskMapper taskRepository) {
        this.taskRepository = taskRepository;
    }

//    @Scheduled(fixedRate = 10000) // 每10秒检查一次
//    public void executeTasks() {
//        List<WorkflowTask> pendingTasks = taskRepository.findByStatus("待执行");
//        for (WorkflowTask task : pendingTasks) {
//            // 模拟任务执行
//            System.out.println("开始执行任务: " + task.getName());
//            task.setStatus("已完成");
//            taskRepository.save(task);
//        }
//    }
}
