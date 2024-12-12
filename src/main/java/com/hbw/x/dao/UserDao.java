package com.hbw.x.dao;

import com.hbw.x.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {



      List<User> findAll();


      User findByOpenId(String openid);
}
