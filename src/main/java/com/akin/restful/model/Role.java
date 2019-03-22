package com.akin.restful.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
    private String roleId;

    @Column(name = "ROLE_NAME", length = 64)
    private String roleName;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7)
    private Date createTime;

    @Column(name = "ROLE_NO", length = 32)
    private String roleNo;

    @Column(name = "MEMO", length = 200)
    private String memo;

    @Column(name = "PARENT_ORG_ID", length = 32)
    private String parentOrgId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private Set<RoleAuthority> roleAuthorities = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")// mappedBy 与当前类关联的类(UserRole)定义此类(Role)的属性
    private Set<UserRole> userRoles = new HashSet<>(0);

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId=roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName=roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo=roleNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo=memo;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId=parentOrgId;
    }

    public Set<RoleAuthority> getRoleAuthorities() {
        return roleAuthorities;
    }

    public void setRoleAuthorities(Set<RoleAuthority> roleAuthorities) {
        this.roleAuthorities=roleAuthorities;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles=userRoles;
    }
}
