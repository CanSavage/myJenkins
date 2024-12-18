package org.example.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.model.SysUser;
import org.example.model.SysUserDTO;
import org.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    UserMapper sum;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> type = new QueryWrapper<>();
        type.eq("name",username);
        SysUser user = sum.selectOne(type);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            return new SysUserDTO(user);
        }
    }


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
//        SysUser sysUser = new SysUser();
//        BCryptPasswordEncoder ad = new BCryptPasswordEncoder();
//        String BCryptPassword = ad.encode(user.getPassword());
//
//        sysUser.setUserName(user.getUsername());
//        sysUser.setUserPassword(BCryptPassword);
//        sysUser.setNickName(user.);
//
//        sum.insert(sysUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }


}
