package com.mybatis.sqlBuilder;

import com.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by ZJH on 2017/8/1.
 */
public class UserSqlBuilder {

    public String buildGetUserByNameOrderByColumn(@Param("name") final String name,
                                                  @Param("columnName") final String columnName) {
        return new SQL() {
            {
                SELECT("*");
                FROM("user");
                WHERE("name like #{name} || '%'");
                ORDER_BY(columnName);
            }
        }.toString();
    }

    public String buildUpdateUser(@Param("user") final User user) {
        return new SQL() {
            {
                UPDATE("user");
                if (user.getName() != null) {
                    SET("name = #{user.name}");
                }
                if (user.getBirthday() != null) {
                    SET("birthday = #{user.birthday}");
                }
                if (user.getSex() != null) {
                    SET("sex = #{user.sex}");
                }
                if (user.getAddress() != null){
                    SET("address = #{user.address}");
                }
                WHERE("id = #{user.id}");
            }
        }.toString();
    }

}
