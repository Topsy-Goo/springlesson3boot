package com.geekbrains.webapp.springlesson3mvc.repos;

import com.geekbrains.webapp.springlesson3mvc.dao.ProductDao;
import com.geekbrains.webapp.springlesson3mvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.geekbrains.webapp.springlesson3mvc.model.Product.isProductValid;

@Repository     //@Component  < Ставится вместо @Repository, когда отсутствует код для работы с БД.
public class ProductRepository
{
    private final ProductDao productDao;

    @Autowired
    public ProductRepository (ProductDao dao)
    {
        productDao = dao;
    }

    @PostConstruct
    public void init()
    {
        //add("Сыр",     "1 кг",  4.50);
        //add("Молоко",  "1 л",   0.80);
        //add("Чай",     "200 г", 2.50);
        //add("Шоколад", "100 г", 1.00);
    }

//--------------------------------------------------------------------*/

    public List<Product> getProductList()
    {
        if (productDao != null)
        {
            return productDao.findAll();
        }
        return null;
    }

//--------------------------------------------------------------------*/

    public boolean add (String title, String measure, double cost)
    {
        Product product = new Product (title, measure, cost);

        if (isProductValid (product) && productDao != null)
        {
            Product p = productDao.saveOrUpdate (product);
            return p != null;
        }
        return false;
    }

    public Product getById (Long id)
    {
        if (productDao != null)
        {
            return productDao.findById(id);
        }
        return null;
    }

    public static List<Product> emptyList() {   return new ArrayList<>();   }


    public boolean changeCostBy (Long id, double delta)
    {
        Product product = getById (id);
        boolean result = productDao != null  &&  product != null  &&  product.changeCostBy (delta);
        if (result)
        {
            productDao.saveOrUpdate (product);
        }
        return result;
    }

}
