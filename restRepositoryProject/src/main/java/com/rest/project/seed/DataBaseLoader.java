package com.rest.project.seed;

import com.rest.project.entity.Producto;
import com.rest.project.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataBaseLoader {

//    private static final Logger log = LoggerFactory.getLogger(DataBaseLoader.class);

    @Value("${deploy.roles}")
    private boolean deploy;
    @Bean
    CommandLineRunner initDataBase(ProductoRepository repository){
        return args -> {
            if (deploy){
                repository.save(
                        new Producto("Galletas", 0.0f, new Date()));
//                log.info("Preloading" + repository.save(
//                        new Producto("Galletas", 12.50f, new Date(new Date().getTime()))));
//                log.info("Preloading" + repository.save(
//                        new Producto("Gelatina", 18.50f, new Date(new Date().getTime()))));
//                log.info("Preloading" + repository.save(
//                        new Producto("Helado", 50.50f, new SimpleDateFormat().parse("1/5/2021"))));
                System.out.println("hola");
            }
        };
    }
}
