package com.mybatis.mapper;

//import com.mybatis.cache.MyRedisCache;
import com.mybatis.cache.MyRedisCache;
import com.mybatis.model.User;
import com.mybatis.sqlBuilder.UserSqlBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ZJH on 2017/7/28.
 */
@Mapper
@CacheNamespace(implementation = MyRedisCache.class)
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    @ResultType(User.class)
    User findUserById(@Param("id") int id) throws Exception;

    @Select("SELECT * FROM user WHERE name LIKE #{name}")  // SELECT * FROM user WHERE name LIKE CONCAT('%',#{name},'%')
    @ResultType(User.class)
    List<User> findUserByName(@Param("name") String name) throws Exception;

    @Insert("INSERT INTO user(id, name, sex, birthday, address) VALUES (NULL ,#{name},#{sex},#{birthday},#{address})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void saveUser(User user) throws Exception;

    @Update("UPDATE user u SET u.name = #{user.name}, u.sex = #{user.sex}, u.birthday = #{user.birthday}, u.address = #{user.address} WHERE u.id = #{user.id}")
    void updateUser(@Param("user") User user) throws Exception;

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(@Param("id") int id) throws Exception;

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUserByNameOrderByColumn")
    @ResultType(User.class)
    List<User> findUserByNameOrderByColumn(@Param("name") String name, @Param("columnName") String columnName) throws Exception;


    @Select("select user.*, role.* from user " +
            "left join user_role on user.id = user_role.user_id " +
            "left join role on user_role.role_id = role.role_id")
    @ResultMap("UserRoleResultMap")
    List<User> findAllUsersWithRoles();

}
