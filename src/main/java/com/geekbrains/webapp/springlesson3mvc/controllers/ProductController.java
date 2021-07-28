package com.geekbrains.webapp.springlesson3mvc.controllers;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import com.geekbrains.webapp.springlesson3mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller     //< Эта аннотация наследует от аннотации @Component
public class ProductController
{
    private final ProductService productService;


    @Autowired
    public ProductController (ProductService service)
    {
        productService = service;
    }


    //http://localhost:8189/store/test
    @GetMapping ("/test")
    @ResponseBody   //< означает, что возвращаемое значение будет ответом клиенту на его
    // запрос. При отсутствии этой аннотации мы должны вернуть название HTML-страницы.
    public String getTest()
    {
        return "Hello, World!";
    }

    //http://localhost:8189/store/echo?word=Uuuuuu
    @GetMapping ("/echo")
    @ResponseBody
    public String getEchoRequest (@RequestParam (name = "word", defaultValue = "..", required = false) String word)
    {   //(Совместное использование  defaultValue и required бессмысленно и преведено для примера.)
        return "Echo: "+ word;
    }

    //http://localhost:8189/store/product
    @GetMapping ("/product")
    @ResponseBody
    public Product getProduct()
    {
        //Если возвращать объект, то он будет перевеёдн в JSON-формат: {"id":1,"title":"сыр","cost":4.5}
        return new Product("сыр", 4.5);
        //Если возвращать строку, то будет использовать метод Product.toString().
    }
}
