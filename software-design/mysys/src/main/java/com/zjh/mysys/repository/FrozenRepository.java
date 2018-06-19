package com.zjh.mysys.repository;

import com.zjh.mysys.entity.Frozen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FrozenRepository extends JpaRepository<Frozen,Integer> {

    @Modifying
    @Query("delete from Frozen f where f.user.userId = :userId")
    void deleteByUserId(@Param("userId") int userId);
}
