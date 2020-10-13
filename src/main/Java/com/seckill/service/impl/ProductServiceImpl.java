package com.seckill.service.impl;

import com.seckill.dao.ProductMapper;
import com.seckill.model.domain.Product;
import com.seckill.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Product getProductById(Integer pid) {
        Product product=null;
        Object o=redisTemplate.opsForValue().get("proinfo"+pid);
        if(o!=null){
            product=(Product)o;
        }else {
            product=productMapper.selectProductById(pid);
            if(product!=null)
                redisTemplate.opsForValue().set("proinfo"+pid,product);
        }
        return product;
    }

    @Override
    public boolean buyById(Integer pid) {
        Integer rest=null;
        String key="pro"+pid;
        Object o=redisTemplate.opsForValue().get(key);
        if(o!=null){
            rest=(Integer)o;
            if(rest>0){
                System.out.println(rest);
                redisTemplate.watch(key);         //CAS操作
                redisTemplate.multi();
                redisTemplate.boundValueOps(key).set(rest-1);
                List<Object> list=redisTemplate.exec();
                if(list!=null)
                    return true;
                else
                    return false;
            }else {
                return false;
            }
        }else {
            rest=productMapper.getRestById(pid);
            if(rest>0) {
                redisTemplate.opsForValue().set("pro" + pid, rest);
                return buyById(pid);
            }else
                return false;
        }
    }
}
