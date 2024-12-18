package org.example.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class SysUser {
    @TableId
    private Integer id;
    private String name;
    private String password;
    private String email;
}
