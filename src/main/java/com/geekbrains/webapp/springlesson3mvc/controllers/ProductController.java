package com.geekbrains.webapp.springlesson3mvc.controllers;

import com.geekbrains.webapp.springlesson3mvc.model.Product;
import com.geekbrains.webapp.springlesson3mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller     //< наследует от аннотации @Component
public class ProductController
{
    private final ProductService productService;


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
        model.addAttribute ("alltheproducts", productService.getAllProducts());
        return "allproducts";
    }

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/showproduct/{id}
    @GetMapping ("/product/{id}")
    public String showProduct (@PathVariable Long id, Model model)
    {
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
        double cost = (double)rub + ((double) cop)/100;

        boolean ok = productService.saveProduct (title, measure, cost);
        String msg = ok ? "Товар создан." : "Не удалось создать товар.";
        model.addAttribute ("prompt", msg);
        return "form";
    }

//------------------ Методы-примеры, не участвующие в приложении: ------------------*/

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/test
    @GetMapping ("/test")
    @ResponseBody   //< означает, что возвращаемое значение будет ответом клиенту на его запрос. При отсутствии этой аннотации мы должны вернуть название HTML-страницы.
    public String getTest()
    {
        return "Hello, World!";
        //Примечательно, что возвращать можно любые объекты, если на странице их есть кому обрабатывать.
    }

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/product
    @GetMapping ("/product")
    @ResponseBody
    public Product getProduct()
    {
        //Если возвращать объект, то он будет перевеёдн в JSON-формат: {"id":1,"title":"сыр","cost":4.5}
        return new Product ("Кофе зерновой", "250 г", 4.5);
        //Если возвращать строку, то будет использовать метод Product.toString().
    }

    //реагирует на GET-запрос с адресом : http://localhost:8189/store/echo?word=Uuuuuu
    @GetMapping ("/echo")
    @ResponseBody
    public String getEchoRequest (@RequestParam (name = "word", defaultValue = "..", required = false) String word)
    {
        //(Совместное использование  defaultValue и required бессмысленно и преведено для примера.)
        return "Echo: "+ word;
    }

}
