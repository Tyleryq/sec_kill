package com.seckill.service;

import com.seckill.model.domain.Product;

public interface IProductService {
    public Product getProductById(Integer pid);
    public boolean buyById(Integer pid);
}
