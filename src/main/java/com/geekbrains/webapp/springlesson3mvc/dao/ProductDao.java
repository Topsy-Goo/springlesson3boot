package com.geekbrains.webapp.springlesson3mvc.dao;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.geekbrains.webapp.springlesson3mvc.repos.ProductRepository.emptyList;

@Component
public class ProductDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDao (SessionFactory sf)
    {
        sessionFactory = sf;
    }


    public Product findById (Long id)
    {
        Product product = null;
        if (sessionFactory != null)
        try (Session session = sessionFactory.getCurrentSession();)
        {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }


    public List<Product> findAll()
    {
        List<Product> productList = emptyList();
        if (sessionFactory != null)
        try (Session session = sessionFactory.getCurrentSession();)
        {
            session.beginTransaction();

            NativeQuery<Product> np = session.createNativeQuery("SELECT * FROM hiber_test.products;", Product.class);
            productList = np.getResultList();

            session.getTransaction().commit();
        }
        return productList;
    }


    public void deleteById (Long id)   // пока не используется
    {
        if (sessionFactory != null)
        try (Session session = sessionFactory.getCurrentSession();)
        {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete (product);
            session.getTransaction().commit();
        }
    }

//Если у продукта id == 0, то он считается новым и для него создаётся запись в БД. В других случаях запись обновляется.
    public Product saveOrUpdate (Product product)
    {
        if (sessionFactory != null && product != null)
        try (Session s = sessionFactory.getCurrentSession();)
        {
            s.beginTransaction();
            s.saveOrUpdate (product);
            s.getTransaction().commit();
        }
        return product;
    }

}
