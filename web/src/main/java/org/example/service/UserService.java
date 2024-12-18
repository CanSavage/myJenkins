package org.example.service;

import org.example.model.SysUser;

import java.util.List;

public interface UserService {
    //查询所有信息，
//
    List<SysUser> select();

    int addUser(SysUser user);

    int deleteUser(int id);
}
