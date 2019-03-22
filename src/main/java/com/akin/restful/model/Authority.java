package com.akin.restful.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Entity
//@Table(name = "TB_SYS_AUTHORITY")
public class Authority {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 32)
    private String authorityId;

    @Column(name = "PARENT_ID", length = 32)
    private String parentId;

    @Column(name = "PARENT_IDS", length = 1000)
    private String parentIds;

    @Column(name = "AUTHORITY_NAME", nullable = false, length = 64)
    private String authorityName;

    @Column(name = "AUTHORITY_TYPE", nullable = false, precision = 22, scale = 0)
    private Integer authorityType;

    @Column(name = "MODULE_NAME", length = 64)
    private String moduleName;

    @Column(name = "URL", length = 200)
    private String url;

    @Column(name = "OPERATION", length = 32)
    private String operation;

    @Column(name = "MENU_NO", length = 32)
    private String menuNo;

    @Column(name = "MEMO", length = 200)
    private String memo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authority")
    private List<RoleAuthority> roleAuthorities = new ArrayList<>();
//    private Set<RoleAuthority> roleAuthorities = new HashSet<>(0);

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Integer getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(Integer authorityType) {
        this.authorityType = authorityType;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<RoleAuthority> getRoleAuthorities() {
        return roleAuthorities;
    }

    public void setRoleAuthorities(List<RoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }
}
