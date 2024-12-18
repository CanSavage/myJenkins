package org.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("workflow_tasks")
public class WorkflowTask {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long workflowId;
    private String name;
    private String status;
    private String executor;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

