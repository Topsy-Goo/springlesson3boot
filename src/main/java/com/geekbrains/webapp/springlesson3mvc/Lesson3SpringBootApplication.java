package com.geekbrains.webapp.springlesson3mvc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication  //< добавлена автоматически при генерировании проекта. (Является псевдонимом для нескольких аннотаций Spring: Configuration, ComponentScan, EnableAutoConfiguration.)
public class Lesson3SpringBootApplication
{
    private static ConfigurableApplicationContext context;

/*  Довольно неожиданно было узнать, что main() завершается почти сразу после начала работы приложения. Видимо, вся работа приложения происходит в другом потоке, а основной поток служит только для инициализации.
*/
    public static void main (String[] args)
    {
        try
        {   context = SpringApplication.run(Lesson3SpringBootApplication.class, args);
        }
        catch (Exception e){e.printStackTrace();}
        finally
        {
            System.out.println("\n\n\t\t\tМетод Lesson3SpringBootApplication.main() завершается.\nПриложение начинает работу.\n\n");
        }
    }


    @Bean // — используется для методов, создающих бины в классе, помеченном аннотацией @Configuration
    private static SessionFactory sessionFactory()
    {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();

        if (!readSqlFile ("import.sql", sf))
            sf = null;
        return sf;
    }

    private static boolean readSqlFile (String strPath, SessionFactory sf)
    {
    /*  Считываем «базу» из sql-файла и скармливаем хибер-ту. (Считывать содержимое SQL-файла
        хибер-т не умеет.) Табуляцию и прочие пробельные символы хибер-т переваривает нормально.

        Эта церемония требуется только при работе jdbc:h2:mem:. MySQL от этой игры сразу «отказыватеся».
    */
        boolean result = false;
        Session session = null;
        try
        {   //sql = Files.lines(Paths.get(fileName)).collect(Collectors.joining(" "));
            String sql = Files.readString (Paths.get(strPath));  //< так тоже прекрасно работает
            session = sf.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
            result = true;
        }
        catch (IOException e)
        {
            sf.close();
            //sf = null;
            e.printStackTrace();
        }
        finally
        {
            if (session != null) session.close();
        }
        return result;
    }

    public static void exit (int exitCode)
    {
        System.out.println("\n\n\t\t\tВыход из приложения.\n\n");
        SpringApplication.exit (context, new AppExitCodeGenerator (18));    //< Непонятно, зачем передавать код, если он используется.
    }

}
