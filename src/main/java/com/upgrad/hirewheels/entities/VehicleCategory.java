package com.upgrad.hirewheels.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class VehicleCategory {
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "vehicle_category_id")
    private int vehicleCategoryId;
    @Column(name = "vehicle_category_name")
    private String vehicleCategoryName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicleCategory")
    private List<VehicleSubCategory> vehicleSubCategories;
}
