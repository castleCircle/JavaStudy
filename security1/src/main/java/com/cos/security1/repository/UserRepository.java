package com.cos.security1.repository;

import com.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Reponsitory가 없어도 Ioc가 됨
public interface UserRepository extends JpaRepository<User,Integer>{

}
