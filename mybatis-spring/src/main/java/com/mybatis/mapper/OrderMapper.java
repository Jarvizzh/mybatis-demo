package com.mybatis.mapper;

//import com.mybatis.cache.MyRedisCache;
import com.mybatis.cache.MyRedisCache;
import com.mybatis.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

/**
 * Created by ZJH on 2017/7/29.
 */
@Mapper
@CacheNamespace(implementation = MyRedisCache.class)
public interface OrderMapper {

    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "order_id", property = "orderId"),
            @Result(column = "order_identity", property = "orderIdentity"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "com.mybatis.mapper.UserMapper.findUserById",
                            fetchType = FetchType.DEFAULT)
            ),
            @Result(column = "order_id", property = "orderDetails",
                    many = @Many(select = "com.mybatis.mapper.OrderDetailMapper.findOrderDetailByOrderId",
                            fetchType = FetchType.LAZY)
            )
    })
    List<Order> findAllOrderWithUserInfo() throws Exception;


    @Select("select * from orders where user_id = #{userId}")
    @Results({
            @Result(id = true, column = "order_id", property = "orderId"),
            @Result(column = "order_identity", property = "orderIdentity"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "com.mybatis.mapper.UserMapper.findUserById",
                            fetchType = FetchType.DEFAULT)
            ),
            @Result(column = "order_id", property = "orderDetails", javaType = Set.class,
                    many = @Many(select = "com.mybatis.mapper.OrderDetailMapper.findOrderDetailByOrderId",
                            fetchType = FetchType.LAZY)
            )
    })
    List<Order> findOrderWithDetailByUserId(@Param("userId") Integer userId) throws Exception;

}
