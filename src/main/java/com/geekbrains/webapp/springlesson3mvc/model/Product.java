package com.geekbrains.webapp.springlesson3mvc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name="products")
public class Product implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")      private Long id;    //< Если у продукта id == 0, то он считается новым и для него создаётся запись в БД.

    @Column(name="title")   private String title;
    @Column(name="measure") private String measure;
    @Column(name="price")   private double cost;

    public Product() {}

//Корректность параметров не проверяем, т.к. это делается перед помещением нового товара в БД. Тогда же товару назначается id.
    public Product (String title, String measure, double cost)
    {
        this.title = title;
        this.measure = measure;
        this.cost = cost;
    }

//----------------------------------------------------------------------*/

    public static boolean isProductValid (Product p)
    {
        return p != null && p.isProductValid();
    }

    public boolean isProductValid()
    {
        return  isTitleValid(title) && isMeasureValid(measure) && isCostValid(cost);
    }

    public static boolean isCostValid (double c) {  return c >= 0.0;  }
    public static boolean isTitleValid (String t)   {  return t != null && !t.trim().isEmpty();  }
    public static boolean isMeasureValid (String m) {  return m != null && !m.trim().isEmpty();  }

//----------------------------------------------------------------------*/

    public Long getId()    {   return id;   }
    public String getTitle()    {   return title;   }
    public String getMeasure()  {   return measure;   }
    public double getCost()    {   return cost;   }

    private void setId (Long val)    {   id = val;   }   //< Этот сеттер нужен только для хибер-та.

    public void setTitle (String title)
    {
        if (!isTitleValid (title))
            throw new IllegalArgumentException();
        this.title = title;
    }

    public void setMeasure (String measure)
    {
        if (!isTitleValid (measure))
            throw new IllegalArgumentException();
        this.measure = measure;
    }

    public boolean setCost (double cost)
    {
        if (isCostValid (cost))
        {
            this.cost = cost;
            return true;
        }
        return false;
    }

//----------------------------------------------------------------------*/

    public boolean changeCostBy (double delta)
    {
        double newvalue = cost + delta;
        if (isCostValid (newvalue))
        {
            cost = newvalue;
            return true;
        }
        return false;
    }

//----------------------------------------------------------------------*/

    public String toString()
    {
        return String.format("Product(id:%d, title:%s, measure:%s, cost:%.2f)",
                             id, title, measure, cost);
    }
}
