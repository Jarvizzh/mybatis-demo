package com.zjh.mysys.repository;

import com.zjh.mysys.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    @Query("select u from Manager u where u.name = :name and u.password = :password")
    Manager getUserByNameAndPass(@Param("name")String name, @Param("password")String password);
}
