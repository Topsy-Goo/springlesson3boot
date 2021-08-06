package com.geekbrains.webapp.springlesson3mvc.controllers;

import com.geekbrains.webapp.springlesson3mvc.Lesson3SpringBootApplication;
import com.geekbrains.webapp.springlesson3mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller     //< наследует от аннотации @Component
public class ProductController
{
    public static final String PROMPT_DEFAULT = "";
    public static final String PROMPT_COST_CHANGED = "Цена товара изменена.";
    public static final String PROMPT_CANNOT_CHANGE_COST = "Не удалось изменить цену товара.";

    private final ProductService productService;
    private String prompt4AllProducts = PROMPT_DEFAULT;


    @Autowired
    public ProductController (ProductService service)
    {
        productService = service;
    }


    //реагирует на GET-запрос с адресом : http://localhost:8189/store
    @GetMapping
    public String showMainPage()
    {
        return "index";
    }

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/showallproducts
    @GetMapping ("/showallproducts")
    public String showAllProducts (Model model) //< Модель помещаем в параметры по мере необходимости.
    {
        if (productService != null)
        {
            model.addAttribute ("alltheproducts", productService.getAllProducts());
        }
        String msg = prompt4AllProducts;
        prompt4AllProducts = PROMPT_DEFAULT;
        model.addAttribute ("prompt", msg);
        return "allproducts";
    }

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/showproduct/{id}
    @GetMapping ("/product/{id}")
    public String showProduct (@PathVariable Long id, Model model)
    {
        if (productService != null)
            model.addAttribute("product", productService.getById(id));
        return "product";
    }

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/create
    @GetMapping ("/create")
    public String formProduct (Model model)
    {
        model.addAttribute ("prompt", "Заполните форму и нажмите кнопку Сохранить.");
        return "form";
    }

    //реагирует на POST-запрос с адресом : http://localhost:8189/store/create
    @PostMapping ("/create")
    public String addProduct (@RequestParam String title, @RequestParam String measure,
                              @RequestParam Integer rub, @RequestParam Integer cop,
                              Model model)
    {
        if (cop == null || cop < 0)    cop = 0;
        if (rub == null || rub < 0)    rub = 0;
        double cost = (double)rub + ((double) cop)/100.0;

        boolean ok = productService != null && productService.saveProduct (title, measure, cost);
        String msg = ok ? "Товар создан." : "Не удалось создать товар.";
        model.addAttribute ("prompt", msg);
        return "form";
    }

    @GetMapping ("/decrease_rub/{id}")
    public String decreaseCostRub (@PathVariable Long id, Model model)
    {
        prompt4AllProducts = (productService != null && productService.changeCostBy (id, -1.0))
                           ? PROMPT_COST_CHANGED
                           : PROMPT_CANNOT_CHANGE_COST;

        return "redirect:/showallproducts";
    }

    @GetMapping ("/increase_rub/{id}")
    public String increaseCostRub (@PathVariable Long id, Model model)
    {
        prompt4AllProducts = (productService != null && productService.changeCostBy (id, 1.0))
                           ? PROMPT_COST_CHANGED
                           : PROMPT_CANNOT_CHANGE_COST;

        return "redirect:/showallproducts";
    }

    @GetMapping ("/exit")
    public String exitApp ()
    {
        Lesson3SpringBootApplication.exit (0);
        return "goodbye";   //< до этой строки очередь не доходит.
    }

}
