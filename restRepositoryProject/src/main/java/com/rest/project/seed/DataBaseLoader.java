package com.rest.project.seed;

import com.rest.project.entity.Producto;
import com.rest.project.entity.Rol;
import com.rest.project.enums.RolNombre;
import com.rest.project.repository.ProductoRepository;
import com.rest.project.repository.RolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class DataBaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DataBaseLoader.class);

    @Value("${deploy.productos}")
    private boolean deployProductos;

    @Value("${deploy.roles}")
    private boolean deployRoles;

    @Value("${deploy.all}")
    private boolean deployAll;

    @Bean
    CommandLineRunner loadProductos(ProductoRepository repository){
        return args -> {
            if (deployProductos || deployAll){
                SimpleDateFormat sDF = new SimpleDateFormat("dd-mm-yyyy");
                try{
                    log.info("Preloading " + repository.save(
                            new Producto("Jugo", 12.50f, sDF.parse("1-2-2021"))));
                    log.info("Preloading " + repository.save(
                            new Producto("Gelatina", 18.50f, sDF.parse("2-1-2021"))));
                    log.info("Preloading " + repository.save(
                            new Producto("Helado", 50.50f, sDF.parse("3-4-2021"))));
                }catch (Exception e){
                    log.error("Error en el metodo loadProductos. " + e.getMessage());
                }
            }
        };
    }

    @Bean
    CommandLineRunner loadRols(RolRepository rolRepository){
        return args -> {
            if (deployRoles || deployAll){
                try {
                    log.info("Preloading " + rolRepository.save(new Rol(RolNombre.ROLE_ADMIN)));
                    log.info("Preloading " + rolRepository.save(new Rol(RolNombre.ROLE_USER)));
                }catch (Exception e){
                    log.error("Error en el metodo loadRols. " + e.getMessage());
                }
            }
        };
    }
}
