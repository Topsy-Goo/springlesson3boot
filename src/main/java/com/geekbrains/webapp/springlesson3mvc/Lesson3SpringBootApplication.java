package com.geekbrains.webapp.springlesson3mvc;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication  //< добавлена автоматически при генерировании проекта
public class Lesson3SpringBootApplication
{
    private static SessionFactory sessionFactory;
    private static ConfigurableApplicationContext context;


    public static void main (String[] args)
    {
        try
        {
            sessionFactory = sessionFactory();
            context = SpringApplication.run(Lesson3SpringBootApplication.class, args);
        }
        catch (Exception e){e.printStackTrace();}
        finally
        {
            if (sessionFactory != null) sessionFactory.close();
        }
    }


    public static void exit (int exitCode)
    {
        SpringApplication.exit (context, new AppExitCodeGenerator (exitCode));
    }

    @Bean private static SessionFactory sessionFactory()
    {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();

        //String sql = null;
        //Session session = null;
        //try
        //{
        //    Files.lines(Paths.get("import.sql")).collect(Collectors.joining(" "));
        //    session = sf.getCurrentSession();
        //    session.beginTransaction();
        //    session.createNativeQuery(sql).executeUpdate();
        //    session.getTransaction().commit();
        //}
        //catch (IOException e)
        //{
        //    sf.close();
        //    sf = null;
        //    e.printStackTrace();
        //}
        //finally
        //{
        //    if (session != null) session.close();
        //}
        return sf;
    }
}
