package com.geekbrains.webapp.springlesson3mvc.services;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import com.geekbrains.webapp.springlesson3mvc.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    private final ProductRepository productRepository;


    @Autowired
    public ProductService (ProductRepository repository)
    {
        productRepository = repository;
    }


    public List<Product> getAllProducts()
    {
        return productRepository != null ? productRepository.getProductList()
                                         : ProductRepository.emptyList();
    }

    public boolean saveProduct (String title, String measure, double cost)
    {
        return productRepository.add (title, measure, cost);
    }

    public boolean saveProduct (Product product)
    {
        return productRepository.add (product);
    }

    public Product getById (Long id)
    {
        if (productRepository != null)
            return productRepository.getById(id);
        return null;
    }

    public boolean changeCostBy (Long id, double delta)
    {
        return productRepository != null
               && productRepository.changeCostBy (id, delta);
    }

}
