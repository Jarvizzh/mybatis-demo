package com.zjh.mysys.repository;

import com.zjh.mysys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.name = :name and u.password = :password")
    User getUserByNameAndPass(@Param("name") String name,@Param("password") String password);

    @Query("select u from User u where u.nickname like CONCAT('%',:name,'%')")
    List<User> findUserByName(@Param("name")String name);

    @Query("select u from User u where u.phone like CONCAT('%',:phone,'%')")
    List<User> findUserByPhone(@Param("phone")String phone);

    @Query("select u from User u where u.hobby like CONCAT('%',:hobby,'%')")
    List<User> findUserByHobby(@Param("hobby")String hobby);
}