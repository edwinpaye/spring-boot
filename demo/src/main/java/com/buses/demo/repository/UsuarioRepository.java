package com.buses.demo.repository;

import com.buses.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombreContainingIgnoreCase(String name);

    List<Usuario> findByNombreNotContainingIgnoreCase(String name);

//    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
//    List<Movie> searchByTitleLike(@Param("title") String title);
//    @Query("SELECT m FROM Movie m WHERE m.rating LIKE ?1%")
//    List<Movie> searchByRatingStartsWith(String rating);
}
