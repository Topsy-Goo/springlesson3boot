package com.geekbrains.webapp.springlesson3mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller     //< Эта аннотация наследует от аннотации @Component
public class ProductController
{

    //http://localhost:8080/store/test
    @GetMapping ("/test")
    @ResponseBody   //< означает, что возвращаемое значение будет ответом клиенту на его
    // запрос. При отсутствии этой аннотации мы должны вернуть название HTML-страницы.
    public String testPath()
    {
        return "Hello, World!";
    }
}
