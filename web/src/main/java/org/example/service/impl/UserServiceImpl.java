package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.model.SysUser;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper sum;

    @Override
    public List<SysUser> select() {
        QueryWrapper<SysUser> type = new QueryWrapper<>();
        List<SysUser> details =sum.selectList(null);
        return details;
    }

    @Override
    public int addUser(SysUser user) {
        BCryptPasswordEncoder ad = new BCryptPasswordEncoder();
        String BCryptPassword = ad.encode(user.getPassword());
        user.setPassword("{bcrypt}"+BCryptPassword);
        return sum.insert(user);
    }

    @Override
    public int deleteUser(int id) {

        return 1;
    }

}
