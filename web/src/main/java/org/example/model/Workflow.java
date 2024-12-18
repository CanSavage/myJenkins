package org.example.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("workflows")
public class Workflow {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
