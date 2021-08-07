package com.geekbrains.webapp.springlesson3mvc.repos;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.geekbrains.webapp.springlesson3mvc.model.Product.isProductValid;

@Repository
public class ProductDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDao (SessionFactory sf)
    {
        sessionFactory = sf;
    }

//-------------- методы затребованы в условии ДЗ-5 -------------------*/

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

            //NativeQuery<Product> nqp = session.createNativeQuery("SELECT * FROM hiber_test.products;", Product.class);    //< для MySQL
            //NativeQuery<Product> nqp = session.createNativeQuery("SELECT * FROM products;", Product.class);     //< для H2
            //productList = nqp.getResultList();

            Query<Product> qp = session.createQuery ("from Product", Product.class);     //< для H2 и для MySQL
            productList = qp.getResultList();

            session.getTransaction().commit();
        }
        return productList;
    }


    public boolean deleteById (Long id)
    {
        if (sessionFactory != null)
        {
            try (Session session = sessionFactory.getCurrentSession();)
            {
                session.beginTransaction();
                Product product = session.get(Product.class, id);
                session.delete (product);
                session.getTransaction().commit();
            }
            return true;
        }
        return false;
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

//-------------- перенесены из ProductRepository ---------------------*/

    public boolean add (String title, String measure, double cost)
    {
        Product product = new Product (title, measure, cost);

        if (isProductValid (product))
        {
            return saveOrUpdate (product) != null;
        }
        return false;
    }

    public boolean changeCostBy (Long id, double delta)
    {
        Product product = findById (id);
        if (product != null  &&  product.changeCostBy (delta))
        {
            return saveOrUpdate (product) != null;
        }
        return false;
    }

    public static List<Product> emptyList() {   return new ArrayList<>();   }

}
