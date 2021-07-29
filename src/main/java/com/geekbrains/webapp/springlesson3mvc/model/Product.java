package com.geekbrains.webapp.springlesson3mvc.model;

public class Product
{
    private static final long MIN_ID = 1;
    private static long idNext = MIN_ID;
    private final Long id;
    private String title;
    private String measure;
    private double cost;

    public Product()
    {
        id = idNext++;
    }

    public Product (String title, String measure, double cost)
    {
        if (!isTitleValid (title) || !isMeasureValid (measure) || !isCostValid (cost))
            throw new IllegalArgumentException();

        id = idNext++;
        this.title = title;
        this.measure = measure;
        this.cost = cost;
    }

//----------------------------------------------------------------------*/

    public static boolean isTitleValid (String t)
    {
        return t != null && !t.trim().isEmpty();
    }

    public static boolean isMeasureValid (String m)
    {
        return m != null && !m.trim().isEmpty();
    }

    public static boolean isCostValid (double c)
    {
        return c >= 0.0;
    }

    public static boolean isIdValid (Long id)  {   return id != null && id >= MIN_ID;   }

//----------------------------------------------------------------------*/

    public Long getId()    {   return id;   }
    public String getTitle()    {   return title;   }
    public String getMeasure()  {   return measure;   }
    public double getCost()    {   return cost;   }

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

    public void setCost (double cost)
    {
        if (!isCostValid (cost))
            throw new IllegalArgumentException();
        this.cost = cost;
    }

//----------------------------------------------------------------------*/

    public String toString()
    {
        return String.format("Product(id:%d, title:%s, measure:%s, cost:%.2f)",
                             id, title, measure, cost);
    }
}
