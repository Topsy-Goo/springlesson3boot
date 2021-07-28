package com.geekbrains.webapp.springlesson3mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //< эта аннотация была добавлена автоматически при генерировании
// проекта.  Спринг ищет бины-контроллеры в папке (и в её подпапках), которая содержит сласс,
// помеченный этой аннотацией.
public class SpringLesson3MvcApplication
{

    public static void main (String[] args)
    {
        SpringApplication.run (SpringLesson3MvcApplication.class, args);
    }

}
