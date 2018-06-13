package com.mybatis.mapper;

//import com.mybatis.cache.MyRedisCache;
import com.mybatis.cache.MyRedisCache;
import com.mybatis.model.Role;
import com.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ZJH on 2017/8/2.
 */
@Mapper
@CacheNamespace(implementation = MyRedisCache.class)
public interface RoleMapper {

    @Select("SELECT * FROM role WHERE role_id = #{id}")
    @ResultType(Role.class)
    Role findRoleById(@Param("id") int id) throws Exception;


    @Select("select r.*, u.* from role as r " +
            "left join user_role as ur on r.role_id = ur.role_id " +
            "left join user as u on u.id = ur.user_id ")
    @ResultMap("RoleUserResultMap")
    List<Role> findAllRoleWithUser();

}
