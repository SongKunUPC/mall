package com.sk.mall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sk.mall.mbg.model.UmsAdmin;
import com.sk.mall.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SongKun on 2020/2/9 6:12 下午
 */
public class AdminUserDetail implements UserDetails {

    private UmsAdmin umsAdmin;
    private List<UmsPermission> permissionList;

    public AdminUserDetail(UmsAdmin umsAdmin,List<UmsPermission> permissionList){
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    // 以下4个boolean值都为true时账户才能正常认证通过
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        // 账户是否失效
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        // 账户是否锁定
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        // 密码是否失效
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        // 是否可用
        return umsAdmin.getStatus().equals(1);
    }
}
