package com.geekbrains.webapp.springlesson3mvc.controllers;

import com.geekbrains.webapp.springlesson3mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller     //< наследует от аннотации @Component
public class ProductController
{
    private final ProductService productService;


    @Autowired
    public ProductController (ProductService service)
    {
        productService = service;
    }

    //http://localhost:8189/store
    @GetMapping
    public String showMainPage()
    {
        return "index";
    }

    //http://localhost:8189/store/showallproducts
    @GetMapping ("/showallproducts")
    public String showAllProducts (Model model) //< Модель помещаем в параметры по мере необходимости.
    {
        model.addAttribute ("alltheproducts", productService.getAllProducts());
        return "allproducts";
    }

    //http://localhost:8189/store/showproduct/{id}
    @GetMapping ("/product/{id}")
    public String showProduct (@PathVariable Long id, Model model)
    {
        model.addAttribute("product", productService.getById(id));
        return "product";
    }

    //http://localhost:8189/store/create
    @GetMapping ("/create")
    public String formProduct (Model model)
    {
        model.addAttribute ("prompt", "Заполните форму и нажмите кнопку Сохранить.");
        return "form";
    }

    @PostMapping ("/create")
    public String addProduct (@RequestParam String title, @RequestParam String measure,
                              @RequestParam Integer rub, @RequestParam Integer cop,
                              Model model)
    {
        if (cop == null || cop < 0)    cop = 0;
        if (rub == null || rub < 0)    rub = 0;
        double cost = (double)rub + ((double) cop)/100;

        boolean ok = productService.saveProduct (title, measure, cost);
        String msg = ok ? "Товар создан." : "Не удалось создать товар.";
        model.addAttribute ("prompt", msg);
        return "form";
    }

}
