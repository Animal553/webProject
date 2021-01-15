package com.qianfeng.service;

import com.qianfeng.dto.Page;
import com.qianfeng.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void deleteProduct(Integer productId);
    Page getProductPage(Integer pageNo, Integer pageSize);
    List<Product> selectProductList();
    Product selectProduct(Integer productId);
}
