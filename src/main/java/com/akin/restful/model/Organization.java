package com.akin.restful.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Organization {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
    private String orgId;

    @Column(name = "ORG_NAME", length = 128)
    private String orgName;

    @Column(name = "PARENT_ID", length = 32)
    private String parentId;

    @Column(name = "PARENT_IDS", length = 1000)
    private String parentIds;

    @Column(name = "ADDRESS", length = 128)
    private String address;

    @Column(name = "POST_CODE", length = 16)
    private String postCode;

    @Column(name = "CONTACT_MAN", length = 32)
    private String contactMan;

    @Column(name = "TELL", length = 16)
    private String tell;

    @Column(name = "FAX", length = 16)
    private String fax;

    @Column(name = "EMAIL", length = 32)
    private String email;

    @Column(name = "STATUS", length = 16)
    private String status;

    @Column(name = "SCHOOL_FLAG", length = 2)
    private String schoolFlag;

    @Column(name = "SCHOOL_CODE", length = 20)
    private String schoolCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
    private List<User> userList = new ArrayList<>();
}
