package com.akin.restful.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "USER_ID", unique = true, nullable = false, length = 32)
    private String userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORG_ID")
    private Organization organization;

    @Column(name = "EMPLOY_NO", length = 16)
    private String employNo;

    @Column(name = "EMPLOY_NAME", length = 64)
    private String employName;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7)
    private Date createTime;

    @Column(name = "SEX",length = 2)
    private String sex;

    @Column(name = "TELL", length = 20)
    private String tell;

    @Column(name = "STATUS", precision = 22, scale = 0)
    private Integer status;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Column(name = "USER_TYPE", length = 2)
    private String userType;

    @Column(name = "PARENT_ORG_ID", length = 32)
    private String parentOrgId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization=organization;
    }

    public String getEmployNo() {
        return employNo;
    }

    public void setEmployNo(String employNo) {
        this.employNo=employNo;
    }

    public String getEmployName() {
        return employName;
    }

    public void setEmployName(String employName) {
        this.employName=employName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex=sex;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell=tell;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status=status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType=userType;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId=parentOrgId;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles=userRoles;
    }
}
