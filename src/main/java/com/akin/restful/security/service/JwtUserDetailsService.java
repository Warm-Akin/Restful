package com.akin.restful.security.service;

import com.akin.restful.model.Authority;
import com.akin.restful.model.User;
import com.akin.restful.model.UserRole;
import com.akin.restful.security.model.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // get user information
        User user = new User();
//        user.setUserName("Akin");
        user.setPassword("5201314");
        // get Authority list
//        List<SimpleGrantedAuthority> collect = sysUser.getRoles().stream().map(SysRole::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        List<Authority> authorityList = new ArrayList<>();
        user.getUserRoles().forEach(userRole -> {
            userRole.getRole().getRoleAuthorities().forEach(roleAuthority -> {
                authorityList.add(roleAuthority.getAuthority());
            });
        });

        return new JwtUser(user.getEmployNo(), user.getPassword(), user.getStatus(), (Collection<? extends GrantedAuthority>) authorityList);
//        return null;
    }
}
