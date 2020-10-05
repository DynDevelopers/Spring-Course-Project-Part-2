package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class City {
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "city_id")
    private int cityId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
    private List<Location> locations;
}
