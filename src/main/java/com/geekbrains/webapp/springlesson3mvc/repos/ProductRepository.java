package com.geekbrains.webapp.springlesson3mvc.repos;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component  //< Поменяем на @Repository, когда появится код для работы с БД.
public class ProductRepository
{
    private final List<Product> productList;


    public ProductRepository()
    {
        productList = emptyList();
    }

    @PostConstruct
    public void init()
    {}

//--------------------------------------------------------------------*/

    public List<Product> getProductList()
    {
        return Collections.unmodifiableList (productList);
    }

//--------------------------------------------------------------------*/

    public boolean add (Product p)
    {
        if (p != null)
            return productList.add (p);
        return false;
    }


    public static List<Product> emptyList() {   return new ArrayList<>();   }
}
