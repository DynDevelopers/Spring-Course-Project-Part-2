package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Repository("vehicleDAO")
public interface VehicleDAO extends JpaRepository<Vehicle, Integer> {


    public Vehicle findById(int id);

    public List<Vehicle> findAll();

    @Transactional
    @Modifying
    @Query("update Vehicle v set v.availabilityStatus = :as where v.vehicleId = :id")
    public void changeAvailability(@Param("id") int id, @Param("as") int availability_status);

    @Query(value = "FROM Vehicle WHERE availability_status = 1")
    public List<Vehicle> findAllByAvailableStatus();

//  FROM Vehicle v INNER JOIN vehiclesubcategory vsc ON v.vehicle_sub_category_vehicle_subcategory_id = vsc.vehiclesubcategoryid INNER JOIN vehiclecategory vc ON vsc.vehicle_category_id = vc.vehicle_category_id AND vc.vehicle_category_name = 'Car' WHERE v.vehicle_id NOT IN (SELECT b.vehicle_vehicle_id FROM Booking b WHERE v.vehicle_id = b.vehicle_vehicle_id AND TO_DATE('19-09-20', 'DD-MM-YY') NOT BETWEEN b.pickup_date AND b.dropoff_date AND TO_DATE('20-09-20', 'DD-MM-YY') NOT BETWEEN b.pickup_date AND b.dropoff_date)
//  @Query(value = "FROM Vehicle v WHERE v.vehicleId NOT IN (SELECT b.vehicle FROM Booking b WHERE :pickUpDate NOT BETWEEN b.pickupDate AND b.dropoffDate AND :dropoffDate NOT BETWEEN b.pickupDate AND b.dropoffDate) AND v.vehicleSubCategory = (SELECT vsc.vehicleSubcategoryId FROM VehicleSubCategory vsc WHERE vsc.vehicleSubcategoryId = (SELECT vc.vehicleCategoryId FROM VehicleCategory vc WHERE vc.vehicleCategoryName = :categoryName)) AND v.location = :location")

    @Query(value = "FROM Vehicle v INNER JOIN VehicleSubCategory vsc ON v.vehicleSubCategory = vsc.vehicleSubCategory INNER JOIN VehicleCategory vc ON vsc.vehicleCategory = vc.vehicleCategory AND vc.vehicleCategoryName = :categoryName WHERE v.vehicle_id NOT IN (SELECT b.vehicle_vehicle_id FROM Booking b WHERE v.vehicle_id = b.vehicle_vehicle_id AND :pickUpDate NOT BETWEEN b.pickup_date AND b.dropoff_date AND :dropoffDate NOT BETWEEN b.pickup_date AND b.dropoff_date)")
    public List<Vehicle> findVehicles(@Param("categoryName") String categoryName, @Param("pickUpDate") ZonedDateTime pickUpDate, @Param("dropoffDate") ZonedDateTime dropoffDate, @Param("location") Location locationId);
}
