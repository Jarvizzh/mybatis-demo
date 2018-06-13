package com.mybatis.mapper;

//import com.mybatis.cache.MyRedisCache;
import com.mybatis.cache.MyRedisCache;
import com.mybatis.model.Item;
import org.apache.ibatis.annotations.*;

/**
 * Created by ZJH on 2017/8/2.
 */
@Mapper
@CacheNamespace(implementation = MyRedisCache.class)
public interface ItemMapper {

    @Select("SELECT * FROM item WHERE item_id = #{id}")
    @Results({
            @Result(id = true, column = "item_id", property = "itemId"),
            @Result(column = "item_name", property = "itemName")
    })
    Item findItemById(int id) throws Exception;

}
