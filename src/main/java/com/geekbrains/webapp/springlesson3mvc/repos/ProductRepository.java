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
    {
        add("Сыр",     "1 кг",  4.50);
        add("Молоко",  "1 л",   0.80);
        add("Чай",     "200 г", 2.50);
        add("Шоколад", "100 г", 1.00);
    }

//--------------------------------------------------------------------*/

    public List<Product> getProductList()
    {
        return Collections.unmodifiableList (productList);
    }

//--------------------------------------------------------------------*/

    public boolean add (String title, String measure, double cost)
    {
        if (Product.isTitleValid(title) && Product.isMeasureValid(measure) && Product.isCostValid(cost))
        {
            return productList.add (new Product(title, measure, cost));
        }
        return false;
    }

    public Product getById (Long id)
    {
        if (Product.isIdValid (id))
        {
            for (Product p : productList)
            {
                if (p.getId().equals(id))
                    return p;
            }
        }
        return null;
    }

    public static List<Product> emptyList() {   return new ArrayList<>();   }
}
