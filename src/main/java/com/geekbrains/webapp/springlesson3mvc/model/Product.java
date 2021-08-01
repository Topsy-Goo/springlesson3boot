package com.geekbrains.webapp.springlesson3mvc.model;

public class Product
{
    private static final long MIN_ID = 1;
    private static long idNext = MIN_ID;
    private final Long id;
    private String title;
    private String measure;
    private double cost;

    //public Product()
    //{
    //    id = idNext++;
    //}

    public Product (String title, String measure, double cost)
    {
        if (isTitleValid (title) && isMeasureValid (measure) && isCostValid (cost))
        {
            id = idNext++;
            this.title = title;
            this.measure = measure;
            this.cost = cost;
        }
        else id = null;    //< индикатор невалидного продукта
    }
/*  Все параметры, кроме id, проверяются при создании объекта. Корректный id служит индикатором валидности объекта.

    Это сделано для того, чтобы приложение не падало из-за брошеных в конструкторе исключений: сделали объект из мусора, проверили id — он оказался невалидным, — обработали ошибку.
*/
    public Product (Product pparam)
    {
        if (pparam != null && isTitleValid (pparam.title) && isMeasureValid (pparam.measure) && isCostValid (cost))
        {
            id = idNext++;
            this.title   = pparam.title;
            this.measure = pparam.measure;
            this.cost    = pparam.cost;
        }
        else id = null;    //< индикатор невалидного продукта
    }

//----------------------------------------------------------------------*/

    public static boolean isProductValid (Product p)  {  return p != null && p.isProductValid();  }

    public boolean isProductValid()  {  return  isIdValid (id);  }

    public static boolean isIdValid (Long id)    {  return id != null && id >= MIN_ID;  }
    public static boolean isCostValid (double c) {  return c >= 0.0;  }
    public static boolean isTitleValid (String t)   {  return t != null && !t.trim().isEmpty();  }
    public static boolean isMeasureValid (String m) {  return m != null && !m.trim().isEmpty();  }

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

    public String toString()
    {
        return String.format("Product(id:%d, title:%s, measure:%s, cost:%.2f)",
                             id, title, measure, cost);
    }
}
