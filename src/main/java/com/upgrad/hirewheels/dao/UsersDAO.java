package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("userDAO")
public interface UsersDAO extends JpaRepository<User, Integer> {
    public List<User> findAll();
    public User findUserByEmail(String email);
    public User findUserByMobileNo(String mobileNo);

    @Transactional
    @Modifying
    @Query("update User u set u.walletMoney = ?2 where u.userId = ?1")
    public void updateWalletMoney(int id, int money);
}
