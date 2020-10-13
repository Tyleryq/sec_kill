package com.seckill.dao;

import com.seckill.model.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper {
    @Select("select * from product where pid=#{pid}")
    public Product selectProductById(Integer pid);
    @Select("select rest from product where pid=#{pid}")
    public Integer getRestById(Integer pid);
}
