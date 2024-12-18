package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.model.WorkflowTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkflowTaskMapper extends BaseMapper<WorkflowTask> {
}