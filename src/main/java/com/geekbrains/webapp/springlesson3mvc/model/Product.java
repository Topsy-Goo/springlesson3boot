package com.geekbrains.webapp.springlesson3mvc.model;

public class Product
{
    private static long idNext = 1;
    private final long id;
    private String title;
    private double cost;

    public Product()
    {
        id = idNext++;
    }
    public Product (String title, double cost)
    {
        if (!isTitleValid (title) || !isCostValid (cost))
            throw new IllegalArgumentException();
        id = idNext++;
        this.title = title;
        this.cost = cost;
    }

//----------------------------------------------------------------------*/

    private boolean isTitleValid (String t)
    {
        return t != null && !t.trim().isEmpty();
    }

    private boolean isCostValid (double c)
    {
        return c >= 0.0;
    }

//----------------------------------------------------------------------*/

    public String getTitle()    {   return title;   }

    public void setTitle (String title)
    {
        if (!isTitleValid (title))
            throw new IllegalArgumentException();
        this.title = title;
    }

    public double getCost()    {   return cost;   }

    public void setCost (double cost)
    {
        if (!isCostValid (cost))
            throw new IllegalArgumentException();
        this.cost = cost;
    }

    public long getId()    {   return id;   }

    public String toString()
    {
        return String.format("Product(id:%d, title:%s, cost:%.2f)", id, title, cost);
    }
}
