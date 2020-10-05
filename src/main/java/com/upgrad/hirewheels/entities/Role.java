package com.upgrad.hirewheels.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Role {
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private List<User> users;
}
