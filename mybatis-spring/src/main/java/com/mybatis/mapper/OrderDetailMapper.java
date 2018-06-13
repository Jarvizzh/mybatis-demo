package com.mybatis.mapper;

//import com.mybatis.cache.MyRedisCache;
import com.mybatis.cache.MyRedisCache;
import com.mybatis.model.OrderDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by ZJH on 2017/8/1.
 */
@Mapper
@CacheNamespace(implementation = MyRedisCache.class)
public interface OrderDetailMapper {

    @Select("SELECT * FROM order_detail WHERE order_id = #{orderId}")
    @Results({
            @Result(id = true, column = "order_detail_id", property = "orderDetailId"),
            @Result(column = "counts", property = "counts"),
            @Result(column = "item_id", property = "item",
                    one = @One(select = "com.mybatis.mapper.ItemMapper.findItemById",
                    fetchType = FetchType.EAGER)
            )
    })
    List<OrderDetail> findOrderDetailByOrderId(@Param("orderId") int orderId) throws Exception;

}
