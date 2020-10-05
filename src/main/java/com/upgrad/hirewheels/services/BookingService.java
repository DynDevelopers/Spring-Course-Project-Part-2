package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Service;

@Service("bookingService")
public interface BookingService {
    public Booking addBooking(Booking booking) throws InsufficientBalanceException;
}
