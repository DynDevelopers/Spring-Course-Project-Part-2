package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR2(50) CHECK(LENGTH(password) > 5)")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "mobile_no", nullable = false, unique = true)
    private String mobileNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

/*  @Column(name = "role_id", nullable = false)
    private String roleId;*/

    @Column(name = "wallet_money", columnDefinition = "NUMBER(10, 2) DEFAULT 10000.00")
    private Integer walletMoney;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Booking> bookings;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Vehicle> vehicles;

    public User() {}
    public User(String firstName, String lastName, String password, String email, String mobileNo, int walletMoney) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.walletMoney = walletMoney;
    }
}
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "roleId")
//    private Role role;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private List<Booking> bookings;