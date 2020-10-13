package com.seckill.controller;

import com.seckill.model.domain.Product;
import com.seckill.model.responsedata.ResponseData;
import com.seckill.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private IProductService productServiceImpl;
    @RequestMapping("/buy/{pid}")
    public ResponseData<Product> buy(@PathVariable("pid") Integer pid){
        if(productServiceImpl.buyById(pid))
            return new ResponseData<Product>(null,200,"抢购成功");
        else
            return new ResponseData<Product>(null,200,"抢购失败");
    }

    @GetMapping("/info/{pid}")
    public ResponseData<Product> infoById(@PathVariable("pid") Integer pid){
        Product product = productServiceImpl.getProductById(pid);
        return new ResponseData<>(product,200,"查询成功");
    }
}
