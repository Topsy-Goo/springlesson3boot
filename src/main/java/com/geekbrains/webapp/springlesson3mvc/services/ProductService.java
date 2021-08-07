package com.geekbrains.webapp.springlesson3mvc.services;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import com.geekbrains.webapp.springlesson3mvc.repos.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    private final ProductDao productDao;


    @Autowired
    public ProductService (ProductDao dao)
    {
        productDao = dao;
    }


    public List<Product> getAllProducts()
    {
        return productDao != null ? productDao.findAll()
                                  : ProductDao.emptyList();
    }

    public boolean saveProduct (String title, String measure, double cost)
    {
        return productDao.add (title, measure, cost);
    }

    public Product getById (Long id)
    {
        if (productDao != null)
            return productDao.findById (id);
        return null;
    }

    public boolean changeCostBy (Long id, double delta)
    {
        return productDao != null
               && productDao.changeCostBy (id, delta);
    }

}
