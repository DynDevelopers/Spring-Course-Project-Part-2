package com.upgrad.hirewheels.services.serviceimpl;

import com.upgrad.hirewheels.dao.BookingDAO;
import com.upgrad.hirewheels.dao.UsersDAO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.InsufficientBalanceException;
import com.upgrad.hirewheels.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Transactional
    @Override
    public Booking addBooking(Booking booking) throws InsufficientBalanceException {
        User user = booking.getUser();
        if ((user.getWalletMoney()-booking.getAmount()) < 0)
            throw new InsufficientBalanceException("Insufficient Balance. Please Check With Admin");

        usersDAO.updateWalletMoney(user.getUserId(), user.getWalletMoney()-booking.getAmount());

        bookingDAO.save(booking);
        return booking;
    }
}
