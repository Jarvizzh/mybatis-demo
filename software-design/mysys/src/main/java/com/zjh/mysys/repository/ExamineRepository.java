package com.zjh.mysys.repository;

import com.zjh.mysys.entity.Examine;
import com.zjh.mysys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamineRepository extends JpaRepository<Examine,Integer> {

}
