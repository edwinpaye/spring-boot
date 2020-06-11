package com.rest.project.controller;

import com.rest.project.dto.AuthLinksResource;
import com.rest.project.dto.JwtDTO;
import com.rest.project.dto.LoginUsuario;
import com.rest.project.dto.NuevoUsuario;
import com.rest.project.entity.Rol;
import com.rest.project.entity.Usuario;
import com.rest.project.enums.RolNombre;
import com.rest.project.repository.RolRepository;
import com.rest.project.repository.UsuarioRepository;
import com.rest.project.security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@BasePathAwareController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    RepositoryEntityLinks repositoryEntityLinks;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario/*, BindingResult bindingResult*/){
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
                passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getEmail());
//        Set<String> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
//        for (String rol : rolesStr) {
//            switch (rol) {
//                case "admin":
//                    Rol rolAdmin = rolRepository.findByRolNombre(RolNombre.ROLE_ADMIN).get();
//                    roles.add(rolAdmin);
//                    break;
//                default:
        Rol rolUser = rolRepository.findByRolNombre(RolNombre.ROLE_USER).get();
        roles.add(rolUser);
//            }
//        }
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return new ResponseEntity("usuario guardado", HttpStatus.CREATED);
    }

    @GetMapping("/hola")
    public ResponseEntity<?> getdatos(){
        return  ResponseEntity.ok(EntityModel.of("hola",
                repositoryEntityLinks.linkFor(AuthController.class).slash("hola").withSelfRel()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtDTO);
    }

    @GetMapping({"", "/"})
    public HttpEntity<AuthLinksResource> listMethods(){
        AuthLinksResource resource = new AuthLinksResource();
        resource.add(linkTo(methodOn(AuthController.class).nuevo(null)).withRel(LinkRelation.of("createAcount")),
                linkTo(methodOn(AuthController.class).login(null, null)).withRel(LinkRelation.of("login")),
                linkTo(methodOn(AuthController.class).getdatos()).withRel(LinkRelation.of("hola")),
                linkTo(methodOn(AuthController.class).listMethods()).withSelfRel());
        return ResponseEntity.ok(resource);
    }

}
