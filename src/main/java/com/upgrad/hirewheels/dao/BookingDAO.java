package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookingDAO")
public interface BookingDAO extends JpaRepository<Booking, Integer> {
//    public void saveBooking(Booking booking);
}
